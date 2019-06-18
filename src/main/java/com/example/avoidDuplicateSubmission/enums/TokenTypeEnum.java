package com.example.avoidDuplicateSubmission.enums;

public enum  TokenTypeEnum {
    /**
     * 不刷新的情况下防止重复提交
     */
    SimplePreventingCommit,
    /**
     * 包含刷新的情况下防止重复提交
     */
    PreventingRefreshCommit;
    ;
}
