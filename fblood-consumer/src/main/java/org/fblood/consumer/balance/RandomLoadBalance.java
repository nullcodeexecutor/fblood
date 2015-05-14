package org.fblood.consumer.balance;

import org.fblood.model.Provider;

import java.util.List;
import java.util.Random;

/**
 * Created by coder on 15/5/14.
 */
public class RandomLoadBalance implements LoadBalance {
    private Random random;

    public RandomLoadBalance() {
        random = new Random();
    }

    @Override
    public Provider balance(List<Provider> providerList) {
        if (providerList.isEmpty()) {
            return null;
        }
        return providerList.get(random.nextInt(providerList.size()));
    }

}
