package com.naman.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QWebCode is a Querydsl query type for WebCode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWebCode extends EntityPathBase<WebCode> {

    private static final long serialVersionUID = -1995580309L;

    public static final QWebCode webCode = new QWebCode("webCode");

    public final StringPath code1 = createString("code1");

    public final StringPath code2 = createString("code2");

    public final NumberPath<Integer> codeno = createNumber("codeno", Integer.class);

    public final StringPath companyid = createString("companyid");

    public final DateTimePath<java.sql.Timestamp> createdate = createDateTime("createdate", java.sql.Timestamp.class);

    public final StringPath descript = createString("descript");

    public final StringPath isuse = createString("isuse");

    public final DateTimePath<java.sql.Timestamp> modifydate = createDateTime("modifydate", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final StringPath name2 = createString("name2");

    public final StringPath name3 = createString("name3");

    public final StringPath name4 = createString("name4");

    public final StringPath tenant_id = createString("tenant_id");

    public QWebCode(String variable) {
        super(WebCode.class, forVariable(variable));
    }

    public QWebCode(Path<? extends WebCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWebCode(PathMetadata metadata) {
        super(WebCode.class, metadata);
    }

}

