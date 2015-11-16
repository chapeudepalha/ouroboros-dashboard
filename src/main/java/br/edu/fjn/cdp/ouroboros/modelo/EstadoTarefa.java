package br.edu.fjn.cdp.ouroboros.modelo;

public enum EstadoTarefa {
	PENDENTE {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Pendente Colaborador";
		}
	},
	AGUARDAACEITACAO {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Aguardando Aceitação";
		}
	},
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
