# Necessidades de partes interessadas (NI):

NI001 - Gerente pode cadastrar mesas. A mesa é um espaço do restaurante com estado disponível ou ocupado.
NI002 - Gerente pode cadastrar ingredientes no estoque (CRUD). Cada ingrediente possui unidade de medida, se é inteiro ou fracionada, se fracionado a quantidade da fração, e se inteiro a quantidade.
NI003 - Gerente pode cadastrar itens de cardápio (item que será vendido). Cada item de cardápio possui N ingredientes, um custo e se ele está disponível baseado nos itens disponíveis em estoque e vontade do gerente.
NI004 - Gerente pode cadastrar funcionários com nome, cpf, email, data de nascimento, telefone, senha do sistema e a sua função.
NI005 - Garçom pode consultar disponibilidade de mesas que retorna as mesas de acordo com o filtro que ele desejar (todas as mesas, só mesas disponíveis).
NI006 - Garçom pode alterar o estado da mesa de disponível para ocupada.
NI007 - Garçom pode iniciar atendimento na mesa ocupada por um cliente. O início do atendimento registra o horário de entrada do cliente no restaurante.
NI008 - Garçom pode criar um pedido em qualquer atendimento aberto. Um pedido possui N itens de cardápio e o garçom precisa confirmar o envio. Cada pedido deduz os ingredientes em estoque. Assim que o pedido é enviado, ele recebe o status pendente.
NI009 - Cozinheiro pode consultar os pedidos enviados. Os pedidos seguem FIFO (First In First Out).
NI010 - Garçom pode buscar pedidos prontos, entregar na mesa e alterar o status do pedido para entregue.
NI011 - Garçom pode encerrar o atendimento. Nenhum atendimento pode encerrar enquanto houver pedido pendente associado a ele. O encerramento do atendimento gera o custo total a ser pago e lista todos os pedidos realizados naquele atendimento. Quando encerra o atendimento, a mesa fica disponível.
NI012 - Gerente pode consultar, em um dado intervalo e/ou por garçom, o quanto de dinheiro entrou, quantidade de atendimentos e quantidade de pedidos.

# Requisitos Funcionais (RF):

RF001 (NI001) - Eu como gerente, quero poder criar, consultar, atualizar e deletar mesas, para poder definir a capacidade do restaurante, e consultar quais mesas estão disponíveis e ocupadas, e alterar o estado de disponibilidade de cada mesa.
Critério de Aceitação CA001 - Este requisito é considerado implementado quando o gerente consegue criar uma mesa, consultar as mesas criadas, alterar o estado da mesa e deletar a mesa.





