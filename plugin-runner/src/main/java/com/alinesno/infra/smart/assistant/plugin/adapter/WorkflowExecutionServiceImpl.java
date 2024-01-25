package com.alinesno.infra.smart.assistant.plugin.adapter;

import com.alinesno.infra.common.facade.wrapper.RpcWrapper;
import com.alinesno.infra.smart.assistant.entity.WorkflowExecutionEntity;
import com.alinesno.infra.smart.assistant.service.IWorkflowExecutionService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class WorkflowExecutionServiceImpl implements IWorkflowExecutionService {
    @Override
    public int getByChainName(String chainName) {
        return 0;
    }

    @Override
    public void deleteByIds(Long[] ids) {

    }

    @Override
    public void deleteByWrapper(RpcWrapper<WorkflowExecutionEntity> wrapper) {

    }

    @Override
    public void update(WorkflowExecutionEntity e) {

    }

    @Override
    public void updateById(WorkflowExecutionEntity e, Long id) {

    }

    @Override
    public boolean updateHasStatus(Long id) {
        return false;
    }

    @Override
    public List<WorkflowExecutionEntity> findByIds(List<String> resourceIds) {
        return null;
    }

    @Override
    public WorkflowExecutionEntity findOne(RpcWrapper<WorkflowExecutionEntity> restWrapper) {
        return null;
    }

    @Override
    public WorkflowExecutionEntity findOne(Serializable id) {
        return null;
    }

    @Override
    public WorkflowExecutionEntity findById(Serializable id) {
        return null;
    }

    @Override
    public List<WorkflowExecutionEntity> findAll() {
        return null;
    }

    @Override
    public List<WorkflowExecutionEntity> findAllByFieldProp(String prop) {
        return null;
    }

    @Override
    public List<WorkflowExecutionEntity> findAllByHasStatus(RpcWrapper<WorkflowExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public List<WorkflowExecutionEntity> findAllByHasStatus() {
        return null;
    }

    @Override
    public List<WorkflowExecutionEntity> findAll(RpcWrapper<WorkflowExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public WorkflowExecutionEntity findEntityById(Serializable id) {
        return null;
    }

    @Override
    public List<WorkflowExecutionEntity> findTop(int number, RpcWrapper<WorkflowExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public List<WorkflowExecutionEntity> findAllByApplicationId(String applicationId, RpcWrapper<WorkflowExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public List<WorkflowExecutionEntity> findAllByApplicationId(String applicationId) {
        return null;
    }

    @Override
    public List<WorkflowExecutionEntity> findAllByTenantId(String tenantId, RpcWrapper<WorkflowExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public List<WorkflowExecutionEntity> findAllByTenantId(String tenantId) {
        return null;
    }

    @Override
    public List<WorkflowExecutionEntity> findAllByTenantIdAndApplicationId(String tenantId, String applicationId) {
        return null;
    }

    @Override
    public List<WorkflowExecutionEntity> findAllByTenantIdAndApplicationId(String tenantId, String applicationId, RpcWrapper<WorkflowExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public Page<WorkflowExecutionEntity> pageRpc(Page<WorkflowExecutionEntity> pageableResult, RpcWrapper<WorkflowExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public boolean saveBatch(Collection<WorkflowExecutionEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<WorkflowExecutionEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<WorkflowExecutionEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(WorkflowExecutionEntity entity) {
        return false;
    }

    @Override
    public WorkflowExecutionEntity getOne(Wrapper<WorkflowExecutionEntity> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Optional<WorkflowExecutionEntity> getOneOpt(Wrapper<WorkflowExecutionEntity> queryWrapper, boolean throwEx) {
        return Optional.empty();
    }

    @Override
    public Map<String, Object> getMap(Wrapper<WorkflowExecutionEntity> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<WorkflowExecutionEntity> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<WorkflowExecutionEntity> getBaseMapper() {
        return null;
    }

    @Override
    public Class<WorkflowExecutionEntity> getEntityClass() {
        return null;
    }
}
