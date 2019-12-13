package com.mountain.business.admin.impl;

import com.mountain.business.admin.FunctionBusiness;
import com.mountain.common.exception.common.CommonException;
import com.mountain.common.exception.function.FunctionException;
import com.mountain.common.util.SystemUtil;
import com.mountain.common.util.response.GlobalStatusCode;
import com.mountain.po.admin.Function;
import com.mountain.repository.admin.FunctionRepository;
import com.mountain.vo.FunctionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FunctionBusinessImpl implements FunctionBusiness {
    @Autowired
    FunctionRepository functionRepository;
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Function save(Function fun) {
        Date date = new Date();
        fun.setCreatedAt(date);
        fun.setUpdatedAt(date);
        functionRepository.save(fun);
        return fun;
    }

    @Override
    public Function update(Function fun) throws CommonException {
        Function oldFun = functionRepository.findById(fun.getFunId()).get();
        Assert.notNull(oldFun);
        SystemUtil.mergeBean(fun, oldFun);
        fun.setUpdatedAt(new Date());
        functionRepository.save(fun);
        return fun;
    }

    @Override
    public void del(Integer id) throws FunctionException {
        Function oldFun = functionRepository.findById(id).get();
        Assert.notNull(oldFun);
        if (functionRepository.findByParentId(id).size() != 0) {
            throw new FunctionException(GlobalStatusCode.CODE_400017.code(), GlobalStatusCode.CODE_400017.value());
        }
        functionRepository.deleteById(id);
    }

    @Override
    public List<FunctionVo> list() {
        List<Function> funs = functionRepository.findAll(new Sort(Direction.ASC, "sort"));
        return functionGroup(funs, null);
    }

    @Override
    public List<FunctionVo> findByUserFuns(Integer accountId) {
        List<Function> funs = listUserFuns(accountId);
        return functionGroup(funs, null);
    }

    @Override
    public List<Function> listUserFuns(Integer accountId) {
        List<Function> funs = functionRepository.findByUserFuns(accountId, new Sort(Direction.ASC, "sort"));
        return funs;
    }


    @Override
    public void sort(Integer[] funIds) {
        Date date = new Date();
        for (int i = 0; i < funIds.length; i++) {
            Function fun = functionRepository.findById(funIds[i]).get();
            if (fun != null) {
                fun.setSort(i);
                fun.setUpdatedAt(date);
                functionRepository.save(fun);
            }
        }
    }

    /**
     * 权限分组
     *
     * @param funs
     * @param parentId
     * @return
     */
    private List<FunctionVo> functionGroup(List<Function> funs, Integer parentId) {
        List<FunctionVo> vos = new ArrayList<FunctionVo>();
        for (Function fun : funs) {
            if ((parentId == null && fun.getParentId() == null) || (parentId != null && parentId.equals(fun.getParentId()))) {
                FunctionVo vo = new FunctionVo();
                vo.setShow(fun.getShow());
                vo.setFunId(fun.getFunId());
                vo.setName(fun.getName());
                vo.setParentId(fun.getParentId());
                vo.setIco(fun.getIco());
                vo.setUrl(fun.getUrl());
                vo.setFunType(fun.getFunType());
                vo.setMethod(fun.getMethod());
                vo.setSort(fun.getSort());
                vo.setFuns(functionGroup(funs, fun.getFunId()));
                vos.add(vo);
            }
        }
        return vos;
    }
}
