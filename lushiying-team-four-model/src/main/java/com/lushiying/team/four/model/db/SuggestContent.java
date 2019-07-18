package com.lushiying.team.four.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yuzhibo
 * @date 2019/7/19 0:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestContent {

    private Long id;

    private Long userId;

    private Long agentId;

    private String suggestContent;

    private Date createTime;

    private Date updateTime;
}
