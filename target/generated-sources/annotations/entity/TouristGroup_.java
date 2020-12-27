package entity;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TouristGroup.class)
public abstract class TouristGroup_ {

	public static volatile SingularAttribute<TouristGroup, Integer> duration;
	public static volatile SingularAttribute<TouristGroup, Mountain> mountain;
	public static volatile SingularAttribute<TouristGroup, Boolean> isAvailableReceipt;
	public static volatile SingularAttribute<TouristGroup, Integer> id;
	public static volatile SetAttribute<TouristGroup, Alpinist> tourists;
	public static volatile SingularAttribute<TouristGroup, LocalDate> startDate;

	public static final String DURATION = "duration";
	public static final String MOUNTAIN = "mountain";
	public static final String IS_AVAILABLE_RECEIPT = "isAvailableReceipt";
	public static final String ID = "id";
	public static final String TOURISTS = "tourists";
	public static final String START_DATE = "startDate";

}

