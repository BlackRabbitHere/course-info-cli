package com.pluralsight.courseinfo.domain.repository;

import java.sql.SQLException;

public class RepositoryException extends Throwable {
    public RepositoryException(String s, SQLException e) {
        super(s,e);
    }
}
