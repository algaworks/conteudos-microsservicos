package com.algaworks.example.resilience4j.produtos;

public class CircuitBreakerConfigTest {
	
//	@Test
//	public void criandoUmCbExecutandoMetodo() {
//		//Cria um CB com as configurações padrão
//		CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("editors");
//
//		//Criando uma implementação fictícia do EditorClient
//		AvaliacaoClient editorTestClient = new AvaliacaoClient() {
//			
//			@Override
//			public AvaliacaoModel getOne(Long id) {
//				//Simula um erro interno no servidor
//				if (id == null) {
//					throw new RuntimeException();
//				}
//				//Simula uma falha de demora na resposta
//				if (id.equals(4L)) {
//					try {
//						Thread.sleep(Duration.ofMinutes(1).toMillis());
//					} catch (Exception e) {}
//				}
//				
//				return new AvaliacaoModel(id, "Alex");
//			}
//
//			@Override
//			public List<AvaliacaoModel> getAll() {
//				return null;
//			}
//			
//		};
//
//		AvaliacaoModel avaliacaoModel = circuitBreaker.executeSupplier(() -> editorTestClient.getOne(1L));
//
//		Assertions.assertThat(avaliacaoModel).isNotNull();
//	}
	
}
