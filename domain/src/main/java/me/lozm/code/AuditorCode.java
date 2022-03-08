package me.lozm.code;

import lombok.Getter;

@Getter
public enum AuditorCode {

    SYSTEM(1L, "SYSTEM")
    ;


    private Long code;
    private String desc;

    AuditorCode(Long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
