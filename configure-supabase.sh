#!/bin/bash

# Script para configurar e testar conexão com Supabase
# Uso: bash configure-supabase.sh

echo "======================================"
echo "  Configuração de Conexão Supabase"
echo "======================================"
echo ""

# Verificar se as variáveis de ambiente já estão configuradas
if [ ! -z "$SUPABASE_HOST" ]; then
    echo "✓ SUPABASE_HOST já configurado: $SUPABASE_HOST"
    read -p "Deseja alterar? (s/n) " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Ss]$ ]]; then
        USE_EXISTING=true
    fi
fi

if [ "$USE_EXISTING" != "true" ]; then
    echo "Entre com suas credenciais Supabase:"
    echo "(Encontre em: app.supabase.com → Project Settings → Database)"
    echo ""
    
    read -p "Host (ex: seu_projeto.supabase.co): " SUPABASE_HOST
    read -p "User (padrão: postgres): " SUPABASE_USER
    SUPABASE_USER=${SUPABASE_USER:-postgres}
    read -sp "Password: " SUPABASE_PASSWORD
    echo ""
    
    # Exportar para a sessão atual
    export SUPABASE_HOST
    export SUPABASE_USER
    export SUPABASE_PASSWORD
fi

echo ""
echo "Configurações definidas:"
echo "  Host: $SUPABASE_HOST"
echo "  User: $SUPABASE_USER"
echo ""

# Compilar
echo "Compilando projeto..."
mvn clean compile

if [ $? -ne 0 ]; then
    echo "✗ Erro na compilação"
    exit 1
fi

echo ""
echo "Testando conexão..."
echo ""

# Testar conexão
mvn exec:java -Dexec.mainClass="bancodadossupabse.ConexaoSupaBase"

if [ $? -eq 0 ]; then
    echo ""
    echo "✓ Teste concluído!"
    echo ""
    echo "Para manter essas configurações no futuro, adicione ao seu ~/.bashrc:"
    echo "  export SUPABASE_HOST='$SUPABASE_HOST'"
    echo "  export SUPABASE_USER='$SUPABASE_USER'"
    echo "  export SUPABASE_PASSWORD='sua_senha'"
else
    echo ""
    echo "✗ Erro na conexão"
    echo "Verifique as credenciais e tente novamente"
    exit 1
fi
