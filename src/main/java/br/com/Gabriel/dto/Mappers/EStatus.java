package br.com.Gabriel.dto.Mappers;


public enum EStatus {
	
	AGUARDANDOPAG(1),
	PAGO(2),
	CANCELADO(3);
	
	private int code;
	
	private EStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	//METODO PARA CONVERTER VALOR NUMERICO PARA TIPO ENUMERADO *static pois vai funcionar sem precisar instanciar
	public static EStatus valueOf(int code) {
		for(EStatus value : EStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		
		throw new IllegalArgumentException("Código de Status Inválido");
	}
	
}
