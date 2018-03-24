package com.example.reactivewebaap.domain;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.data.cassandra.core.cql.Ordering.DESCENDING;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

/*@PrimaryKeyClass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode*/
public class CustomerKey implements Serializable {

    @PrimaryKeyColumn(name = "first_name", type = PARTITIONED)
    private String firstName;

    @PrimaryKeyColumn(name = "date_of_birth", ordinal = 0)
    private LocalDateTime dateOfBirth;

    @PrimaryKeyColumn(name = "customer_id", ordinal = 1, ordering = DESCENDING)
    private UUID id;

}
