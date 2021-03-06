package model.violations;

/**
 * Representation of a Trip object
 */
public class VOMovingViolations {

	private String objectId, row, location, addresId, streetSegid,  xCoord, yCoord, ticketType, fineAmt, totalPaid, penalty1, penalty2, accidentIndicator, agencyid, ticketsIssuedate, violationCode, violationDesc, row_id;

	public VOMovingViolations(String pObjectId, String pRow, String pLocation, String pAddresId, String pStreetSegid, String pXCoord, String pYCoord, String pTicketType, String pFineAmt, String pTotalPaid, String pPenalty1, String pPenalty2, String pAccidentIndicator, String pAgencyid, String pTicketsIssuedate, String pViolationCode, String pViolationDesc, String pRow_id){
		objectId= pObjectId;
		row=pRow;
		location=pLocation;
		addresId=pAddresId;
		streetSegid= pStreetSegid;
		xCoord=pXCoord;
		yCoord=pYCoord;
		ticketType=pTicketType;
		fineAmt=pFineAmt;
		totalPaid=pTotalPaid;
		penalty1=pPenalty1;
		penalty2=pPenalty2;
		accidentIndicator=pAccidentIndicator;
		agencyid = pAgencyid;
		ticketsIssuedate=pTicketsIssuedate;
		violationCode=pViolationCode;
		violationDesc=pViolationDesc;
		row_id = pRow_id;
	}
	@Override
	public String toString() {
		return "VOMovingViolations [objectId()=" + getObjectId() + ",\n getLocation()=" + getLocation()
				+ ",\n getTicketIssueDate()=" + getTicketIssueDate() + ",\n getTotalPaid()=" + getTotalPaid()
				+ ",\n getAccidentIndicator()=" + getAccidentIndicator() + ",\n getViolationDescription()="
				+ getViolationDescription() + ",\n getStreetSegId()=" + getStreetSegId() + ",\n getAddressId()="
				+ getAddressId() + "]\n\n";
	}


	/**
	 * @return id - Identificador �nico de la infracci�n
	 */
	public String getObjectId() {
		// TODO Auto-generated method stub
		return objectId;
	}	
	
	
	/**
	 * @return location - Direcci�n en formato de texto.
	 */
	public String getLocation() {
		// TODO Auto-generated method stub
		return location;
	}
	
	/**
	 * @return agencyid - Direcci�n en formato de texto.
	 */
	public String getAgencyId() {
		// TODO Auto-generated method stub
		return agencyid;
	}

	/**
	 * @return date - Fecha cuando se puso la infracci�n .
	 */
	public String getTicketIssueDate() {
		// TODO Auto-generated method stub
		return ticketsIssuedate;
	}
	
	/**
	 * @return totalPaid - Cuanto dinero efectivamente pag� el que recibi� la infracci�n en USD.
	 */
	public Double getTotalPaid() {
		// TODO Auto-generated method stub
		return Double.parseDouble(totalPaid);
	}
	
	/**
	 * @return accidentIndicator - Si hubo un accidente o no.
	 */
	public String  getAccidentIndicator() {
		// TODO Auto-generated method stub
		return accidentIndicator;
	}
		
	/**
	 * @return description - Descripci�n textual de la infracci�n.
	 */
	public String  getViolationDescription() {
		// TODO Auto-generated method stub
		return violationDesc;
	}
	public String  getViolationCode() {
		// TODO Auto-generated method stub
		return violationCode;
	}
	
	public String getStreetSegId() {
		return streetSegid;
	}
	
	public String getAddressId() {
		return addresId;
	}
    public String getKeyCoord(){
    	return xCoord+"-"+yCoord;
    }
    public String getXCoord(){
    	return xCoord;
    }
    public String getYCoord(){
    	return yCoord;
    }
}