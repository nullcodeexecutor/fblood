package org.fblood.consumer.balance;

import org.fblood.model.Provider;

import java.util.List;

/**
 * Created by coder on 15/5/14.
 */
public interface LoadBalance {

    Provider balance(List<Provider> providerList);

}
