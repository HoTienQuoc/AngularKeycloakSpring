package com.keycloakAngularSpring.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    // ✅ Inject UserDetailsService được cung cấp ở nơi khác (ví dụ CustomUserDetailsService)
    private final UserDetailsService userDetailsService;

    /**
     * ✅ Bean mã hóa mật khẩu: BCrypt (chuẩn hiện tại)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * ✅ AuthenticationProvider cấu hình cho Dao-based authentication
     * ⚠️ Hai API dưới đây bị đánh dấu @Deprecated nhưng vẫn là cách chính thức trong Spring 6.5.x.
     * ⚙️ Spring 7.x sẽ thay bằng builder API: DaoAuthenticationProvider.withUserDetailsService(...)
     */
    @SuppressWarnings("deprecation")
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * ✅ AuthenticationManager: lấy từ AuthenticationConfiguration
     * Đây là cách chính thức trong Spring Security 6+,
     * giúp Spring tự động quản lý danh sách AuthenticationProvider.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuditorAware<Integer> auditorAware(){
        return new ApplicationAuditAware();
    }
}
