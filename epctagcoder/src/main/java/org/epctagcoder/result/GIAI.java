package org.epctagcoder.result;

public class GIAI extends Base {
	private String individualAssetReference;

	public GIAI() {

	}

	public String getIndividualAssetReference() {
		return individualAssetReference;
	}

	public void setIndividualAssetReference(String individualAssetReference) {
		this.individualAssetReference = individualAssetReference;
	}

	
	@Override
	public String toString() {
		StringBuilder json = new StringBuilder();
		
		json.append(String.format("{ \"epcScheme\": \"%s\"", getEpcScheme()))
				.append(String.format(", \"applicationIdentifier\": \"%s\"", getApplicationIdentifier()))
				.append(String.format(", \"tagSize\": \"%s\"", getTagSize()))
				.append(String.format(", \"filterValue\": \"%s\"", getFilterValue()))
				.append(String.format(", \"partitionValue\": \"%s\"", getPartitionValue()))
				.append(String.format(", \"prefixLength\": \"%s\"", getPrefixLength()))
				.append(String.format(", \"companyPrefix\": \"%s\"", getCompanyPrefix()))
				.append(String.format(", \"individualAssetReference\": \"%s\"", getIndividualAssetReference()))
				.append(String.format(", \"epcPureIdentityURI\": \"%s\"", getEpcPureIdentityURI()))
				.append(String.format(", \"epcTagURI\": \"%s\"", getEpcTagURI()))
				.append(String.format(", \"epcRawURI\": \"%s\"", getEpcRawURI()))
				.append(String.format(", \"binary\": \"%s\"", getBinary()))
				.append(String.format(", \"rfidTag\": \"%s\"", getRfidTag())).append(" }");
		
		return json.toString();
	}













	

}
