#!/bin/bash

# ═══════════════════════════════════════════════════════════════════════════
# Script de Teste - Sistema Restaurante Duke Gourmet
# ═══════════════════════════════════════════════════════════════════════════

echo "╔════════════════════════════════════════════════════════════╗"
echo "║   🧪 TESTE DO SISTEMA RESTAURANTE DUKE GOURMET 🧪          ║"
echo "╚════════════════════════════════════════════════════════════╝"
echo ""

# Cores
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Teste 1: Verificar se arquivos existem
echo -e "${YELLOW}[TESTE 1] Verificando se arquivos existem...${NC}"
ARQUIVOS=(
    "src/restauranterevisaooo/Cliente.java"
    "src/restauranterevisaooo/Produto.java"
    "src/restauranterevisaooo/Bebida.java"
    "src/restauranterevisaooo/PratoPrincipal.java"
    "src/restauranterevisaooo/Sobremesa.java"
    "src/restauranterevisaooo/ItemPedido.java"
    "src/restauranterevisaooo/Pedido.java"
    "src/restauranterevisaooo/Delivery.java"
    "src/restauranterevisaooo/Restaurante.java"
    "src/restauranterevisaooo/MetodoPagamento.java"
    "src/restauranterevisaooo/PagamentoCartao.java"
    "src/restauranterevisaooo/PagamentoPix.java"
    "src/restauranterevisaooo/StatusPedido.java"
    "src/restauranterevisaooo/RestauranteException.java"
    "src/restauranterevisaooo/RestauranteMain.java"
)

TODOS_EXISTEM=true
for arquivo in "${ARQUIVOS[@]}"; do
    if [ -f "$arquivo" ]; then
        echo -e "  ${GREEN}✅${NC} $arquivo"
    else
        echo -e "  ${RED}❌${NC} $arquivo NÃO ENCONTRADO"
        TODOS_EXISTEM=false
    fi
done

if [ "$TODOS_EXISTEM" = true ]; then
    echo -e "${GREEN}[TESTE 1] ✅ PASSOU${NC}"
else
    echo -e "${RED}[TESTE 1] ❌ FALHOU${NC}"
    exit 1
fi

echo ""

# Teste 2: Compilar
echo -e "${YELLOW}[TESTE 2] Compilando arquivos Java...${NC}"
javac -d target/classes -sourcepath src src/restauranterevisaooo/*.java 2>&1 > /tmp/compile.log

if [ $? -eq 0 ]; then
    echo -e "${GREEN}[TESTE 2] ✅ Compilação bem-sucedida${NC}"
else
    echo -e "${RED}[TESTE 2] ❌ Falha na compilação${NC}"
    cat /tmp/compile.log
    exit 1
fi

echo ""

# Teste 3: Verificar classes compiladas
echo -e "${YELLOW}[TESTE 3] Verificando classes compiladas...${NC}"
CLASSES=(
    "target/classes/restauranterevisaooo/Cliente.class"
    "target/classes/restauranterevisaooo/Produto.class"
    "target/classes/restauranterevisaooo/Bebida.class"
    "target/classes/restauranterevisaooo/PratoPrincipal.class"
    "target/classes/restauranterevisaooo/Sobremesa.class"
    "target/classes/restauranterevisaooo/ItemPedido.class"
    "target/classes/restauranterevisaooo/Pedido.class"
    "target/classes/restauranterevisaooo/Delivery.class"
    "target/classes/restauranterevisaooo/Restaurante.class"
    "target/classes/restauranterevisaooo/MetodoPagamento.class"
    "target/classes/restauranterevisaooo/PagamentoCartao.class"
    "target/classes/restauranterevisaooo/PagamentoPix.class"
    "target/classes/restauranterevisaooo/StatusPedido.class"
    "target/classes/restauranterevisaooo/RestauranteException.class"
    "target/classes/restauranterevisaooo/RestauranteMain.class"
)

TODOS_COMPILADOS=true
for classe in "${CLASSES[@]}"; do
    if [ -f "$classe" ]; then
        echo -e "  ${GREEN}✅${NC} $(basename $classe)"
    else
        echo -e "  ${RED}❌${NC} $(basename $classe) NÃO ENCONTRADA"
        TODOS_COMPILADOS=false
    fi
done

if [ "$TODOS_COMPILADOS" = true ]; then
    echo -e "${GREEN}[TESTE 3] ✅ PASSOU${NC}"
else
    echo -e "${RED}[TESTE 3] ❌ FALHOU${NC}"
    exit 1
fi

echo ""

# Teste 4: Verificar se pode executar (apenas mostra menu, não interativo)
echo -e "${YELLOW}[TESTE 4] Testando se aplicação inicia...${NC}"
echo "5" | java -cp target/classes restauranterevisaooo.RestauranteMain > /tmp/run.log 2>&1
if grep -q "DUKE GOURMET" /tmp/run.log; then
    echo -e "${GREEN}[TESTE 4] ✅ Aplicação iniciou corretamente${NC}"
else
    echo -e "${RED}[TESTE 4] ❌ Aplicação não iniciou${NC}"
    cat /tmp/run.log
    exit 1
fi

echo ""

# Teste 5: Contar linhas de código
echo -e "${YELLOW}[TESTE 5] Estatísticas do projeto...${NC}"
TOTAL_LINHAS=$(find src/restauranterevisaooo -name "*.java" -exec wc -l {} + | tail -1 | awk '{print $1}')
TOTAL_ARQUIVOS=$(find src/restauranterevisaooo -name "*.java" | wc -l)
echo "  Arquivos Java: $TOTAL_ARQUIVOS"
echo "  Total de linhas: $TOTAL_LINHAS"
echo -e "${GREEN}[TESTE 5] ✅ Estatísticas coletadas${NC}"

echo ""

# Resultado Final
echo "╔════════════════════════════════════════════════════════════╗"
echo -e "║${GREEN}           ✅ TODOS OS TESTES PASSARAM! ✅${NC}              ║"
echo "╚════════════════════════════════════════════════════════════╝"
echo ""
echo "📊 Resumo:"
echo "  • $TOTAL_ARQUIVOS arquivos Java"
echo "  • $TOTAL_LINHAS linhas de código"
echo "  • ✅ Compilação bem-sucedida"
echo "  • ✅ Aplicação executa corretamente"
echo ""
echo "🚀 Para executar:"
echo "   java -cp target/classes restauranterevisaooo.RestauranteMain"
echo ""
