package me.lozm.api.global.restDocs;

import org.springframework.restdocs.snippet.Attributes;

import static org.springframework.restdocs.snippet.Attributes.key;

public interface DocumentFormatGenerator {

    static Attributes.Attribute getDateFormat() {
        return key("format").value("yyyy-MM-dd");
    }

    static Attributes.Attribute getDateTimeFormat() {
        return key("format").value("yyyy-MM-dd'T'HH:mm:ss");
    }

    static Attributes.Attribute getYnFormat() {
        return key("format").value("사용: Y, 미사용: N");
    }

    static Attributes.Attribute getDuplicationCheckFormat() {
        return key("format").value("한 주: ONE_WEEK, 한 달: ONE_MONTH");
    }

    static Attributes.Attribute getGuideTypeFormat() {
        return key("format").value("전체: ALL, 일반: GENERAL, 우선: WOOSUN");
    }

    static Attributes.Attribute getCustomerGender() {
        return key("format").value("남성: MAN, 여성: WOMAN");
    }

    static Attributes.Attribute getCustomerAge() {
        return key("format").value("소년: JUVENILE, 청년: YOUTH, 장년: MATURE, 노년: ELD");
    }
}