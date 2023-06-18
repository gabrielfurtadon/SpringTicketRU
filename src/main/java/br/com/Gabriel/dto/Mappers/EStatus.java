package br.com.Gabriel.dto.Mappers;

public enum EStatus {

	PENDENTE {
		@Override
		public String toString() {
			return "PENDENTE";
		}
	},

	PAGO {
		@Override
		public String toString() {
			return "PAGO";
		}
	},

	CANCELADO {
		@Override
		public String toString() {
			return "CANCELADO";
		}
	};

}
