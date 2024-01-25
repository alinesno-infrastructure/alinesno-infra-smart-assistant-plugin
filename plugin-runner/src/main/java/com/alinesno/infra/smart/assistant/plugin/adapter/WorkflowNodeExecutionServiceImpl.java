package com.alinesno.infra.smart.assistant.plugin.adapter;

import com.alinesno.infra.common.facade.wrapper.RpcWrapper;
import com.alinesno.infra.smart.assistant.entity.WorkflowNodeExecutionEntity;
import com.alinesno.infra.smart.assistant.service.IWorkflowNodeExecutionService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.lettuce.core.dynamic.annotation.Command;

import com.alinesno.infra.smart.assistant.service.IWorkflowNodeExecutionService;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class WorkflowNodeExecutionServiceImpl implements IWorkflowNodeExecutionService {
    @Override
    public void deleteByIds(Long[] ids) {

    }

    @Override
    public void deleteByWrapper(RpcWrapper<WorkflowNodeExecutionEntity> wrapper) {

    }

    @Override
    public void update(WorkflowNodeExecutionEntity e) {

    }

    @Override
    public void updateById(WorkflowNodeExecutionEntity e, Long id) {

    }

    @Override
    public boolean updateHasStatus(Long id) {
        return false;
    }

    @Override
    public List<WorkflowNodeExecutionEntity> findByIds(List<String> resourceIds) {
        return null;
    }

    @Override
    public WorkflowNodeExecutionEntity findOne(RpcWrapper<WorkflowNodeExecutionEntity> restWrapper) {
        return null;
    }

    @Override
    public WorkflowNodeExecutionEntity findOne(Serializable id) {
        return null;
    }

    @Override
    public WorkflowNodeExecutionEntity findById(Serializable id) {
        return null;
    }

    @Override
    public List<WorkflowNodeExecutionEntity> findAll() {
        return null;
    }

    @Override
    public List<WorkflowNodeExecutionEntity> findAllByFieldProp(String prop) {
        return null;
    }

    @Override
    public List<WorkflowNodeExecutionEntity> findAllByHasStatus(RpcWrapper<WorkflowNodeExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public List<WorkflowNodeExecutionEntity> findAllByHasStatus() {
        return null;
    }

    @Override
    public List<WorkflowNodeExecutionEntity> findAll(RpcWrapper<WorkflowNodeExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public WorkflowNodeExecutionEntity findEntityById(Serializable id) {
        return null;
    }

    @Override
    public List<WorkflowNodeExecutionEntity> findTop(int number, RpcWrapper<WorkflowNodeExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public List<WorkflowNodeExecutionEntity> findAllByApplicationId(String applicationId, RpcWrapper<WorkflowNodeExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public List<WorkflowNodeExecutionEntity> findAllByApplicationId(String applicationId) {
        return null;
    }

    @Override
    public List<WorkflowNodeExecutionEntity> findAllByTenantId(String tenantId, RpcWrapper<WorkflowNodeExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public List<WorkflowNodeExecutionEntity> findAllByTenantId(String tenantId) {
        return null;
    }

    @Override
    public List<WorkflowNodeExecutionEntity> findAllByTenantIdAndApplicationId(String tenantId, String applicationId) {
        return null;
    }

    @Override
    public List<WorkflowNodeExecutionEntity> findAllByTenantIdAndApplicationId(String tenantId, String applicationId, RpcWrapper<WorkflowNodeExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public Page<WorkflowNodeExecutionEntity> pageRpc(Page<WorkflowNodeExecutionEntity> pageableResult, RpcWrapper<WorkflowNodeExecutionEntity> wrapper) {
        return null;
    }

    @Override
    public boolean saveBatch(Collection<WorkflowNodeExecutionEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<WorkflowNodeExecutionEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<WorkflowNodeExecutionEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(WorkflowNodeExecutionEntity entity) {
        return false;
    }

    @Override
    public WorkflowNodeExecutionEntity getOne(Wrapper<WorkflowNodeExecutionEntity> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Optional<WorkflowNodeExecutionEntity> getOneOpt(Wrapper<WorkflowNodeExecutionEntity> queryWrapper, boolean throwEx) {
        return Optional.empty();
    }

    @Override
    public Map<String, Object> getMap(Wrapper<WorkflowNodeExecutionEntity> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<WorkflowNodeExecutionEntity> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<WorkflowNodeExecutionEntity> getBaseMapper() {
        return null;
    }

    @Override
    public Class<WorkflowNodeExecutionEntity> getEntityClass() {
        return null;
    }
}
