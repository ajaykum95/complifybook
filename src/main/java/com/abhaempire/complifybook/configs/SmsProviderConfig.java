package com.abhaempire.complifybook.configs;

import com.abhaempire.complifybook.enums.SmsProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SmsProviderConfig {
    private SmsProvider activeSmsProvider;
}
