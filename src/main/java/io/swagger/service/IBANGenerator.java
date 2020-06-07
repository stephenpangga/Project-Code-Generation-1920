package io.swagger.service;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;
public class IBANGenerator extends SequenceStyleGenerator {
    public static final String CODE_NUMBER_SEPARATOR_PARAMETER = "codeNumberSeparator";
    public static final String CODE_NUMBER_SEPARATOR_DEFAULT = "INH0";

    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    public static final String NUMBER_FORMAT_DEFAULT = "%02d";
    private String format;
    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        return String.format(format, super.generate(session, object));    }
    @Override
    public void configure(Type type, Properties params,
                          ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        String codeNumberSeparator = ConfigurationHelper.getString(CODE_NUMBER_SEPARATOR_PARAMETER, params, CODE_NUMBER_SEPARATOR_DEFAULT);
        String numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);

        this.format ="NL"+"%1$02d"+codeNumberSeparator+numberFormat;
    }



}