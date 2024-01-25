package com.alinesno.infra.smart.assistant.plugin.adapter;

import com.alinesno.infra.common.facade.wrapper.RpcWrapper;
import com.alinesno.infra.smart.assistant.entity.RoleChainEntity;
import com.alinesno.infra.smart.assistant.service.IRoleChainService;
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
public class RoleChainServiceImpl implements IRoleChainService {
    @Override
    public void runById(Long chainId) {

    }

    @Override
    public void saveRoleChain(RoleChainEntity entity) {

    }

    @Override
    public void updateRoleChain(RoleChainEntity entity) {

    }

    @Override
    public void deleteByIds(Long[] ids) {

    }

    @Override
    public void deleteByWrapper(RpcWrapper<RoleChainEntity> wrapper) {

    }

    @Override
    public void update(RoleChainEntity e) {

    }

    @Override
    public void updateById(RoleChainEntity e, Long id) {

    }

    @Override
    public boolean updateHasStatus(Long id) {
        return false;
    }

    @Override
    public List<RoleChainEntity> findByIds(List<String> resourceIds) {
        return null;
    }

    @Override
    public RoleChainEntity findOne(RpcWrapper<RoleChainEntity> restWrapper) {
        return null;
    }

    @Override
    public RoleChainEntity findOne(Serializable id) {
        return null;
    }

    @Override
    public RoleChainEntity findById(Serializable id) {
        return null;
    }

    @Override
    public List<RoleChainEntity> findAll() {
        return null;
    }

    @Override
    public List<RoleChainEntity> findAllByFieldProp(String prop) {
        return null;
    }

    @Override
    public List<RoleChainEntity> findAllByHasStatus(RpcWrapper<RoleChainEntity> wrapper) {
        return null;
    }

    @Override
    public List<RoleChainEntity> findAllByHasStatus() {
        return null;
    }

    @Override
    public List<RoleChainEntity> findAll(RpcWrapper<RoleChainEntity> wrapper) {
        return null;
    }

    @Override
    public RoleChainEntity findEntityById(Serializable id) {
        return null;
    }

    @Override
    public List<RoleChainEntity> findTop(int number, RpcWrapper<RoleChainEntity> wrapper) {
        return null;
    }

    @Override
    public List<RoleChainEntity> findAllByApplicationId(String applicationId, RpcWrapper<RoleChainEntity> wrapper) {
        return null;
    }

    @Override
    public List<RoleChainEntity> findAllByApplicationId(String applicationId) {
        return null;
    }

    @Override
    public List<RoleChainEntity> findAllByTenantId(String tenantId, RpcWrapper<RoleChainEntity> wrapper) {
        return null;
    }

    @Override
    public List<RoleChainEntity> findAllByTenantId(String tenantId) {
        return null;
    }

    @Override
    public List<RoleChainEntity> findAllByTenantIdAndApplicationId(String tenantId, String applicationId) {
        return null;
    }

    @Override
    public List<RoleChainEntity> findAllByTenantIdAndApplicationId(String tenantId, String applicationId, RpcWrapper<RoleChainEntity> wrapper) {
        return null;
    }

    @Override
    public Page<RoleChainEntity> pageRpc(Page<RoleChainEntity> pageableResult, RpcWrapper<RoleChainEntity> wrapper) {
        return null;
    }

    @Override
    public boolean saveBatch(Collection<RoleChainEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<RoleChainEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<RoleChainEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(RoleChainEntity entity) {
        return false;
    }

    @Override
    public RoleChainEntity getOne(Wrapper<RoleChainEntity> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Optional<RoleChainEntity> getOneOpt(Wrapper<RoleChainEntity> queryWrapper, boolean throwEx) {
        return Optional.empty();
    }

    @Override
    public Map<String, Object> getMap(Wrapper<RoleChainEntity> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<RoleChainEntity> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<RoleChainEntity> getBaseMapper() {
        return null;
    }

    @Override
    public Class<RoleChainEntity> getEntityClass() {
        return null;
    }
}
