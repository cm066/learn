package com.cm.business.service;

import com.cm.business.entity.Integrals;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;

public interface IntegralsService {

    Object toInsert(String xid);
}
