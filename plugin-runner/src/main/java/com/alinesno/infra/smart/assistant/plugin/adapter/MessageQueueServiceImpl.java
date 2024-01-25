package com.alinesno.infra.smart.assistant.plugin.adapter;

import com.alinesno.infra.common.facade.wrapper.RpcWrapper;
import com.alinesno.infra.smart.assistant.entity.MessageQueueEntity;
import com.alinesno.infra.smart.assistant.service.IMessageQueueService;
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
public class MessageQueueServiceImpl implements IMessageQueueService {
    @Override
    public void addMessage(MessageQueueEntity messageQueue) {

    }

    @Override
    public void runMessage(MessageQueueEntity messageQueue) {

    }

    @Override
    public MessageQueueEntity queryMessage(String businessId) {
        return null;
    }

    @Override
    public boolean queryQueueStatus(String businessId) {
        return false;
    }

    @Override
    public boolean updateAssistantContent(String businessId, String resultMap) {
        return false;
    }

    @Override
    public void deleteByIds(Long[] ids) {

    }

    @Override
    public void deleteByWrapper(RpcWrapper<MessageQueueEntity> wrapper) {

    }

    @Override
    public void update(MessageQueueEntity e) {

    }

    @Override
    public void updateById(MessageQueueEntity e, Long id) {

    }

    @Override
    public boolean updateHasStatus(Long id) {
        return false;
    }

    @Override
    public List<MessageQueueEntity> findByIds(List<String> resourceIds) {
        return null;
    }

    @Override
    public MessageQueueEntity findOne(RpcWrapper<MessageQueueEntity> restWrapper) {
        return null;
    }

    @Override
    public MessageQueueEntity findOne(Serializable id) {
        return null;
    }

    @Override
    public MessageQueueEntity findById(Serializable id) {
        return null;
    }

    @Override
    public List<MessageQueueEntity> findAll() {
        return null;
    }

    @Override
    public List<MessageQueueEntity> findAllByFieldProp(String prop) {
        return null;
    }

    @Override
    public List<MessageQueueEntity> findAllByHasStatus(RpcWrapper<MessageQueueEntity> wrapper) {
        return null;
    }

    @Override
    public List<MessageQueueEntity> findAllByHasStatus() {
        return null;
    }

    @Override
    public List<MessageQueueEntity> findAll(RpcWrapper<MessageQueueEntity> wrapper) {
        return null;
    }

    @Override
    public MessageQueueEntity findEntityById(Serializable id) {
        return null;
    }

    @Override
    public List<MessageQueueEntity> findTop(int number, RpcWrapper<MessageQueueEntity> wrapper) {
        return null;
    }

    @Override
    public List<MessageQueueEntity> findAllByApplicationId(String applicationId, RpcWrapper<MessageQueueEntity> wrapper) {
        return null;
    }

    @Override
    public List<MessageQueueEntity> findAllByApplicationId(String applicationId) {
        return null;
    }

    @Override
    public List<MessageQueueEntity> findAllByTenantId(String tenantId, RpcWrapper<MessageQueueEntity> wrapper) {
        return null;
    }

    @Override
    public List<MessageQueueEntity> findAllByTenantId(String tenantId) {
        return null;
    }

    @Override
    public List<MessageQueueEntity> findAllByTenantIdAndApplicationId(String tenantId, String applicationId) {
        return null;
    }

    @Override
    public List<MessageQueueEntity> findAllByTenantIdAndApplicationId(String tenantId, String applicationId, RpcWrapper<MessageQueueEntity> wrapper) {
        return null;
    }

    @Override
    public Page<MessageQueueEntity> pageRpc(Page<MessageQueueEntity> pageableResult, RpcWrapper<MessageQueueEntity> wrapper) {
        return null;
    }

    @Override
    public boolean saveBatch(Collection<MessageQueueEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<MessageQueueEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<MessageQueueEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(MessageQueueEntity entity) {
        return false;
    }

    @Override
    public MessageQueueEntity getOne(Wrapper<MessageQueueEntity> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Optional<MessageQueueEntity> getOneOpt(Wrapper<MessageQueueEntity> queryWrapper, boolean throwEx) {
        return Optional.empty();
    }

    @Override
    public Map<String, Object> getMap(Wrapper<MessageQueueEntity> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<MessageQueueEntity> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<MessageQueueEntity> getBaseMapper() {
        return null;
    }

    @Override
    public Class<MessageQueueEntity> getEntityClass() {
        return null;
    }
}
