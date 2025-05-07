HttpSecurityConfig.class
Deshabilitar CSRF, generalmente NO Recomendado para Aplicaciones Web con Sesión.
Deshabilitar CSRF, solo se recomienda para APIs completamente stateless (que no utilizan cookies de sesión para mantener el estado de autenticación, como las APIs que usan JWT) o para casos muy específicos donde entiendes los riesgos.