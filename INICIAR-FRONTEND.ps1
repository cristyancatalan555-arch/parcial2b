$ErrorActionPreference = 'Stop'
Set-Location (Join-Path $PSScriptRoot 'parcial2b-frontend')
if (-not (Test-Path node_modules)) {
    npm.cmd install
    if ($LASTEXITCODE -ne 0) { throw 'No se pudieron instalar las dependencias.' }
}
npm.cmd run dev
