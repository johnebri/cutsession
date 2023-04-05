package com.johnebri.cutsession.dao;

import com.johnebri.cutsession.model.StudioSession;
import com.johnebri.cutsession.model.User;
import com.johnebri.cutsession.model.enums.StudioSessionTypeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author macbookpro on 12/6/22
 */
@Slf4j
@Component
public class StudioSessionDao implements DAO<StudioSession>{

    private final JdbcTemplate jdbcTemplate;

    public StudioSessionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<StudioSession> rowMapper = (rs, rowNum) -> {
        StudioSession ss = new StudioSession();
        ss.setId(rs.getString("id"));
        ss.setMerchantId(rs.getString("merchant_id"));
        ss.setStartsAt(rs.getString("starts_at"));
        ss.setEndsAt(rs.getString("ends_at"));
        ss.setType(rs.getString("type"));
        return ss;
    };

    @Override
    public List<StudioSession> list() {
        return null;
    }

    @Override
    public void create(StudioSession ss) {
        String sql = "INSERT INTO studio_sessions(id, merchant_id, starts_at, ends_at, type) VALUES (?, ?, ?, ?, ?)";
        int insert = jdbcTemplate.update(sql, ss.getId(), ss.getMerchantId(), ss.getStartsAt(), ss.getEndsAt(), ss.getType().toString());
        if(insert == 1) {
            log.info("Studio session created successfully");
        }
    }

    @Override
    public Optional<StudioSession> get(int id) {
        return Optional.empty();
    }

    @Override
    public void update(StudioSession studioSession, int id) {

    }

    @Override
    public void delete(int id) {

    }

    public List<StudioSession> findByMerchantId(String merchantId) {
        String sql = "SELECT * FROM studio_sessions WHERE merchant_id = ?";
        return jdbcTemplate.query(sql, rowMapper, new Object[]{merchantId});
    }

    public Optional<StudioSession> findBySessionId(String sessionId) {
        String sql = "SELECT * FROM studio_sessions WHERE id = ?";
        StudioSession studioSession = null;
        try {
            studioSession = jdbcTemplate.queryForObject(sql, new Object[]{sessionId}, rowMapper);
        } catch (DataAccessException ex) {
            log.error("Studio session not found");
        }
        return Optional.ofNullable(studioSession);
    }

}
