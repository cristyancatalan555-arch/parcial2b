param([string]$Usuario = 'root', [string]$PuertoMySQL = '3306')
$ErrorActionPreference = 'Stop'
$env:DB_USER = $Usuario
$env:DB_URL = "jdbc:mysql://localhost:$PuertoMySQL/parcial2b"
$clave = Read-Host 'Contraseña de MySQL (Enter si no tiene)' -AsSecureString
$env:DB_PASSWORD = [System.Net.NetworkCredential]::new('', $clave).Password
try {
    Set-Location (Join-Path $PSScriptRoot 'parcial2b')
    if (Test-Path 'target/parcial2b-0.0.1-SNAPSHOT.jar') {
        java -jar target/parcial2b-0.0.1-SNAPSHOT.jar
    } else {
        mvn spring-boot:run
    }
} finally { Remove-Item Env:DB_PASSWORD -ErrorAction SilentlyContinue }
