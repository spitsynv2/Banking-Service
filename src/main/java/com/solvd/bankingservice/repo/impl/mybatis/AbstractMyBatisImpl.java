package com.solvd.bankingservice.repo.impl.mybatis;

import com.solvd.bankingservice.repo.IDAO;
import com.solvd.bankingservice.repo.IDAOCommonUtility;
import com.solvd.bankingservice.util.MyBatisLoader;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-13
 */
public abstract class AbstractMyBatisImpl<T,ID> implements IDAO<T,ID>, IDAOCommonUtility<T,ID>  {
    private static final Logger log = LogManager.getLogger(AbstractMyBatisImpl.class);

    @Override
    public T readById(ID id) {
        try (SqlSession session = MyBatisLoader.getSqlSessionFactory().openSession()) {
            Object mapper = session.getMapper(getMapperClass());
            IDAO<T, ID> daoMapper = (IDAO<T, ID>) mapper;

            T entity = daoMapper.readById(id);

            log.info("Entity: {} was successfully read from database", entity);
            return entity;
        } catch (Exception e) {
            log.error("Error in readById: {}", id, e);
            return null;
        }
    }

    @Override
    public void deleteById(ID id) {
        try (SqlSession session = MyBatisLoader.getSqlSessionFactory().openSession()) {
            Object mapper = session.getMapper(getMapperClass());
            IDAO<T, ID> daoMapper = (IDAO<T, ID>) mapper;

            daoMapper.deleteById(id);
            session.commit();

            log.info("Successfully deleteById: {} from {} table.", id, getTableName());
        } catch (Exception e) {
            log.error("Error in deleteById: {} from {} table.", id, getTableName(), e);
        }
    }

    @Override
    public void create(T entity) {
        try (SqlSession session = MyBatisLoader.getSqlSessionFactory().openSession()) {
            Object mapper = session.getMapper(getMapperClass());
            IDAO<T, ID> daoMapper = (IDAO<T, ID>) mapper;

            daoMapper.create(entity);
            session.commit();

            log.info("Successfully inserted {} to {} table.", entity, getTableName());
        } catch (Exception e) {
            log.error("Error in insertion: {} to {} table.", entity, getTableName(), e);
        }
    }

    @Override
    public void update(T entity) {
        try (SqlSession session = MyBatisLoader.getSqlSessionFactory().openSession()) {
            Object mapper = session.getMapper(getMapperClass());
            IDAO<T, ID> daoMapper = (IDAO<T, ID>) mapper;

            daoMapper.update(entity);
            session.commit();

            log.info("Successfully updated {} in {} table.", entity, getTableName());
        } catch (Exception e) {
            log.error("Error in update: {} in {} table.", entity, getTableName(), e);
        }
    }

    @Override
    public List<T> readAllByForeignKeyId(ID foreignKeyId) {
        try (SqlSession session = MyBatisLoader.getSqlSessionFactory().openSession()) {
            Object mapper = session.getMapper(getMapperClass());
            IDAOCommonUtility<T, ID> IDAOCommonUtility = (IDAOCommonUtility<T, ID>) mapper;

            List<T> entityList = IDAOCommonUtility.readAllByForeignKeyId(foreignKeyId);

            if (entityList != null && !entityList.isEmpty()) {
                log.info("{}List: {} were successfully readAllByForeignKeyId from database table: {}",
                        entityList.get(0).getClass().getSimpleName(), entityList, getTableName());
            } else {
                log.info("No records found in database table: {}", getTableName());
            }

            return entityList;
        } catch (Exception e) {
            log.error("Error in readAllByForeignKeyId: {}, from table: {}", foreignKeyId, getTableName(), e);
            return null;
        }
    }

    protected abstract Class<?> getMapperClass();

    protected abstract String getTableName();
}
