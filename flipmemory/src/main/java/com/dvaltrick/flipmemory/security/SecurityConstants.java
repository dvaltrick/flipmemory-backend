package com.dvaltrick.flipmemory.security;

/**
 * SecurityConstants
 */
public class SecurityConstants {
    public static final String SECRET = "3d62bbb01894757513b3278ece92d5b38e9980751519d57282c4a6475d46514f07c6781c03ae0dce034f4d0f4d32db080df62cc78c0f0a62f720fe3aa850499b";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/userentity";
    public static final String RECOVERY_URL = "/api/userentity/recovery/{username}";
}