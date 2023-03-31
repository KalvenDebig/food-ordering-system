package food.ordering.system.system.order.service.domain.valueobject;

import com.food.ordering.system.domain.valueobject.BaseId;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */

/**
 * <p>The uniqueness is only important in aggregates, here Long value is enough</p>
 */
public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
