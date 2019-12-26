/**
 * 
 */
package xml.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import xml.dbcalls.PojoInsertion;
import xml.parsers.Xpath;
import xml.pojos.Code;
import xml.pojos.Location;

/**
 * @author sakkenapelly
 *
 */
public class PoParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Xpath xpath = new Xpath();
		File file = new File("input.xml");
		xml.pojos.PoHeader poHeader = new xml.pojos.PoHeader();
		Map<String, String> hashMap = null;
		xml.pojos.Status status = new xml.pojos.Status();
		xml.dbcalls.PojoInsertion pojoInsertion = new PojoInsertion();
		Location location = new Location();
		List<Location> locationsList = new ArrayList<>();
		List<Code> codesList = new ArrayList<>();
		Code code = new Code();

		/* status table insertion*/
		
		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/Status/Code", file);
		status.setStatusCode(hashMap.get("Code"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/Status/EffectiveDateTime",
				file);
		status.setEffectiveDatetime(hashMap.get("EffectiveDateTime"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/Status/ArchiveIndicator",
				file);
		if (hashMap.get("ArchiveIndicator").equals("true")) {
			status.setArchiveIndicator(true);
		} else {
			status.setArchiveIndicator(false);
		}

		status.setId((long) pojoInsertion.insertStatusPojo(status));
		poHeader.setStatus(status);

		/* location table insertion */
		
		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/CustomerParty/Location",
				file);
		location.setLocationType(hashMap.get("type"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/CustomerParty/Location/ID", file);
		location.setLocationId(hashMap.get("ID"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/CustomerParty/Location/Name", file);
		location.setLocationName(hashMap.get("Name"));

		location.setId((pojoInsertion.insertLocationsPojo(location)));
		locationsList.add(location);
		
		location=new Location();
		
		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/SupplierParty/Location/Address", file);
		location.setAddressType((hashMap.get("type")));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/SupplierParty/Location/Address/AttentionOfName",
				file);
		location.setAttentionOfName((hashMap.get("AttentionOfName")));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/SupplierParty/Location/Address/AddressLine",
				file);
		location.setAddressLineSequence1((hashMap.get("AddressLine")));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/SupplierParty/Location/Address/CityName",
				file);
		location.setCityName(hashMap.get("CityName"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/SupplierParty/Location/Address/CountrySubDivisionCode",
				file);
		location.setCountrySubDivisionCode(hashMap.get("CountrySubDivisionCode"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/SupplierParty/Location/Address/CountryCode",
				file);
		location.setCountryCode(hashMap.get("CountryCode"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/SupplierParty/Location/Address/PostalCode",
				file);
		location.setPostalCode(hashMap.get("PostalCode"));
		location.setId((pojoInsertion.insertLocationsPojo(location)));
		locationsList.add(location);

		location=new Location();
		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipToParty/Location",
				file);
		location.setLocationType((hashMap.get("type")));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipToParty/Location/ID",
				file);
		location.setLocationId((hashMap.get("ID")));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipToParty/Location/Name", file);
		location.setLocationName((hashMap.get("Name")));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipToParty/Location/Address", file);
		location.setAddressType((hashMap.get("type")));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipToParty/Location/Address/AttentionOfName",
				file);
		location.setAttentionOfName(hashMap.get("AttentionOfName"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipToParty/Location/Address/AddressLine",
				file);
		location.setAddressLineSequence1((hashMap.get("AddressLine")));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipToParty/Location/Address/CityName",
				file);
		location.setCityName(hashMap.get("CityName"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipToParty/Location/Address/CountrySubDivisionCode",
				file);
		location.setCountrySubDivisionCode(hashMap.get("CountrySubDivisionCode"));
		
		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipToParty/Location/Address/CountryCode",
				file);
		location.setCountryCode(hashMap.get("CountryCode"));
		
		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipToParty/Location/Address/PostalCode",
				file);
		location.setPostalCode(hashMap.get("PostalCode"));
		location.setId((pojoInsertion.insertLocationsPojo(location)));
		locationsList.add(location);
		
		location=new Location();
		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipFromParty/PartyIDs/ID",
				file);
		location.setLocationId(((hashMap.get("ID"))));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipFromParty/Name",
				file);
		location.setLocationName(((hashMap.get("Name"))));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipFromParty/Location/Address", file);
		location.setAddressType(((hashMap.get("Name"))));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipFromParty/Location/Address/AttentionOfName", file);
		location.setAttentionOfName((hashMap.get("AttentionOfName")));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipFromParty/Location/Address/AddressLine",
				file);
		location.setAddressLineSequence1((hashMap.get("AddressLine")));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipFromParty/Location/Address/CityName",
				file);
		location.setCityName(((hashMap.get("CityName"))));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipFromParty/Location/Address/CountrySubDivisionCode",
				file);
		location.setCountrySubDivisionCode((hashMap.get("CountrySubDivisionCode")));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipToParty/Location/Address/CountryCode",
				file);
		location.setCountryCode((hashMap.get("CountryCode")));
			
		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipToParty/Location/Address/PostalCode",
				file);
		location.setPostalCode(hashMap.get("PostalCode"));
		location.setId((pojoInsertion.insertLocationsPojo(location)));
		locationsList.add(location);
		
		/* code table insertion */
		
		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/Classification/Code/Code",
				file);
		code.setCode(hashMap.get("Code"));
		code.setListId(hashMap.get("listID"));
		code.setCode(hashMap.get("sequence"));
		poHeader.setClassificationCode(pojoInsertion.insertCodesPojo(code));
		codesList.add(code);
		

		/* header table insertion */
		
		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/DocumentID/ID", file);
		poHeader.setAccountingEntity(hashMap.get("accountingEntity"));
		poHeader.setVariationId(Integer.parseInt(hashMap.get("variationID")));
		poHeader.setLogicalId(hashMap.get("lid"));
		poHeader.setDocumentId(hashMap.get("ID"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/AlternateDocumentID/ID",
				file);
		poHeader.setAlternateDocumentId(hashMap.get("ID"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/DisplayID", file);
		poHeader.setDisplayId(hashMap.get("DisplayID"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/LastModificationDateTime",
				file);
		poHeader.setLastModificationDatetime(hashMap.get("LastModificationDateTime"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/LastModificationPerson/IDs/ID", file);

		poHeader.setLastModificationPersonId(Integer.parseInt(hashMap.get("ID")));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/LastModificationPerson/Name", file);
		poHeader.setLastModificationPersonName(hashMap.get("Name"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/DocumentDateTime", file);
		poHeader.setDocumentDatetime(hashMap.get("DocumentDateTime"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/SupplierParty/PartyIDs/ID", file);
		poHeader.setSupplierPartyId(hashMap.get("ID"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/SupplierParty/Name", file);
		poHeader.setSupplierPartyName(hashMap.get("Name"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipFromParty/PartyIDs/ID", file);
		poHeader.setShipFromPartyId(hashMap.get("ID"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ShipFromParty/Name", file);
		poHeader.setShipFromPartyName(hashMap.get("Name"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ExtendedAmount", file);
		poHeader.setExtendedAmount(Long.parseLong(hashMap.get("ExtendedAmount")));
		poHeader.setExtendedAmountCurrencyId(hashMap.get("currencyID"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ExtendedBaseAmount",
				file);
		poHeader.setExtendedBaseAmount(Long.parseLong(hashMap.get("ExtendedBaseAmount")));
		poHeader.setExtendedBaseAmountCurrencyId(hashMap.get("currencyID"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/ExtendedReportAmount",
				file);
		poHeader.setExtendedAmount(Long.parseLong(hashMap.get("ExtendedReportAmount")));
		poHeader.setExtendedReportAmountCurrencyId(hashMap.get("currencyID"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/CanceledAmount", file);
		poHeader.setCanceledAmount(Long.parseLong(hashMap.get("CanceledAmount")));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/CanceledBaseAmount",
				file);
		poHeader.setCanceledBaseAmount(Long.parseLong(hashMap.get("CanceledBaseAmount")));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/CanceledReportingAmount",
				file);
		poHeader.setCanceledAmount(Long.parseLong(hashMap.get("CanceledReportingAmount")));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/PaymentTerm", file);
		poHeader.setPaymentTermType((hashMap.get("type")));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/PaymentTerm/IDs/ID",
				file);
		poHeader.setPaymentTermId(hashMap.get("ID"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/PaymentTerm/Description",
				file);
		poHeader.setPaymentTermDescription(hashMap.get("Description"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/PaymentTerm/PaymentTermCode", file);
		poHeader.setPaymentTermCode(hashMap.get("PaymentTermCode"));
		poHeader.setPaymentTermListId(hashMap.get("listID"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/OrderDateTime", file);
		poHeader.setOrderDatetime(hashMap.get("OrderDateTime"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/BuyerParty/Contact/ID",
				file);
		poHeader.setBuyerId(hashMap.get("ID"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/UserArea/Property/NameValue", file);
		poHeader.setUserAreaPropertyName(hashMap.get("name"));
		poHeader.setUserAreaPropertyType(hashMap.get("type"));
		poHeader.setUserAreaPropertyameValue(hashMap.get("NameValue"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/DocumentUsageCode", file);
		poHeader.setDocumentUsageCode(hashMap.get("DocumentUsageCode"));

		hashMap = xpath.getData("SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/BaseCurrencyAmount",
				file);
		poHeader.setBaseCurrencyAmountType(hashMap.get("type"));

		hashMap = xpath.getData(
				"SyncPurchaseOrder/DataArea/PurchaseOrder/PurchaseOrderHeader/BaseCurrencyAmount/Amount", file);
		poHeader.setBaseCurrencyAmount(Long.parseLong(hashMap.get("Amount")));
		poHeader.setBaseCurrencyAmountId(hashMap.get("currencyID"));
		pojoInsertion.insertPoHeaderPojo(poHeader);
		pojoInsertion.insertStatusPojo(status);
		pojoInsertion.insertCodesPojo(code);

	}
}
