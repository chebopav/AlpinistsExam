package entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Alpinist.class)
public abstract class Alpinist_ {

	public static volatile SingularAttribute<Alpinist, String> address;
	public static volatile SingularAttribute<Alpinist, String> name;
	public static volatile ListAttribute<Alpinist, TouristGroup> touristGroups;
	public static volatile SingularAttribute<Alpinist, Integer> id;
	public static volatile SingularAttribute<Alpinist, Integer> age;

	public static final String ADDRESS = "address";
	public static final String NAME = "name";
	public static final String TOURIST_GROUPS = "touristGroups";
	public static final String ID = "id";
	public static final String AGE = "age";

}

