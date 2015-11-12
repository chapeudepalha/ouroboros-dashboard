package br.edu.fjn.cdp.ouroboros.modelo;

public enum EstadoTarefa {

	PARAFAZER {
		@Override
		public String toString() {
			return "Para Fazer";
		}
	},
	EMPROGRESSO {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Em Progresso";
		}
	},
	CONCLUIDO {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Concluído";
		}
	}

}
