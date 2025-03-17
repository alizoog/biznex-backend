package com.biznex.model.translation;


import com.biznex.common.constant.Name;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
public class TranslationMin {
    private Long id;
    private Name name;
    private String tag;

    public TranslationMin(ResultSet rs) throws SQLException {
        this.id = rs.getLong("id");
        this.name = new Name(rs.getString("name_uz"), rs.getString("name_en"), rs.getString("name_ru"));
        this.tag = rs.getString("tag_name");
    }
}

