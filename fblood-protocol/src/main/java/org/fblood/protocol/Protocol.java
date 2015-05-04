package org.fblood.protocol;

import org.fblood.model.Provider;

/**
 * Created by coder on 15/5/1.
 */
public interface Protocol {

    void publishService(Provider provider, Object service) throws Exception;

    Object getService(Provider provider) throws Exception;


}
