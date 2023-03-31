package food.ordering.system.system.order.service.domain.valueobject;

import com.food.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */

/**
 * A simple TrackingId class only extends BaseId class
 */
public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
