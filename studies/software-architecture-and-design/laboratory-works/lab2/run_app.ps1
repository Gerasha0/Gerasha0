# =============================================================================
# Script for running Food Delivery System
# Automatically sets UTF-8 encoding for proper Ukrainian character display
# =============================================================================

# Colors for output messages
$Green = "Green"
$Yellow = "Yellow"
$Red = "Red"
$Cyan = "Cyan"

Write-Host "=== FOOD DELIVERY SYSTEM STARTUP ===" -ForegroundColor $Cyan
Write-Host ""

# Setup UTF-8 encoding
Write-Host "[1/4] Setting up UTF-8 encoding..." -ForegroundColor $Yellow
try {
    # Set UTF-8 code page
    chcp 65001 | Out-Null
    
    # Configure PowerShell console encoding
    [Console]::OutputEncoding = [System.Text.Encoding]::UTF8
    [Console]::InputEncoding = [System.Text.Encoding]::UTF8
    
    Write-Host "  [OK] UTF-8 encoding set successfully" -ForegroundColor $Green
} catch {
    Write-Host "  [!] Warning: Could not set UTF-8 encoding" -ForegroundColor $Yellow
    Write-Host "      Possible issues with Ukrainian character display" -ForegroundColor $Yellow
}

# Check project folder exists
Write-Host "[2/4] Checking project folder..." -ForegroundColor $Yellow
$ProjectPath = "."

if (-not (Test-Path $ProjectPath)) {
    Write-Host "  [X] ERROR: Project folder not found: $ProjectPath" -ForegroundColor $Red
    Write-Host "      Make sure script is run from project directory" -ForegroundColor $Red
    Read-Host "Press Enter to exit"
    exit 1
}
Write-Host "  [OK] Project folder found" -ForegroundColor $Green

# Check compiled classes
Write-Host "[3/4] Checking compiled classes..." -ForegroundColor $Yellow
$ClassesPath = "target\classes"
$MainClass = "target\classes\com\example2\App.class"

if (-not (Test-Path $MainClass)) {
    Write-Host "  [!] Compiled classes not found" -ForegroundColor $Yellow
    Write-Host "      Attempting to compile project..." -ForegroundColor $Yellow
    
    try {
        $compileResult = mvn clean compile 2>&1
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host "  [OK] Project compiled successfully" -ForegroundColor $Green
        } else {
            Write-Host "  [X] ERROR: Could not compile project" -ForegroundColor $Red
            Write-Host "      Make sure Maven is installed and configured" -ForegroundColor $Red
            Write-Host "      Error details:" -ForegroundColor $Red
            Write-Host $compileResult -ForegroundColor $Red
            Read-Host "Press Enter to exit"
            exit 1
        }
    } catch {
        Write-Host "  [X] ERROR: Maven not found or compilation error" -ForegroundColor $Red
        Write-Host "      Make sure Maven is installed and in PATH" -ForegroundColor $Red
        Read-Host "Press Enter to exit"
        exit 1
    }
} else {
    Write-Host "  [OK] Compiled classes found" -ForegroundColor $Green
}

# Run application
Write-Host "[4/4] Starting application..." -ForegroundColor $Yellow
Write-Host "========================================" -ForegroundColor $Cyan
Write-Host ""

try {
    # Run application using Maven exec plugin for proper dependency handling
    mvn compile exec:java "-Dexec.mainClass=com.example2.App" "-Dexec.jvmArgs=-Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Duser.language=uk -Duser.country=UA" -q
    
    $exitCode = $LASTEXITCODE
    Write-Host ""
    Write-Host "========================================" -ForegroundColor $Cyan
    
    if ($exitCode -eq 0) {
        Write-Host "  [OK] Application completed successfully" -ForegroundColor $Green
    } else {
        Write-Host "  [!] Application finished with code: $exitCode" -ForegroundColor $Yellow
    }
    
} catch {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor $Cyan
    Write-Host "  [X] ERROR: Could not start application" -ForegroundColor $Red
    Write-Host "      Make sure Java and Maven are installed and configured" -ForegroundColor $Red
    Write-Host "      Check JAVA_HOME environment variable" -ForegroundColor $Red
    Write-Host "      Error details: $($_.Exception.Message)" -ForegroundColor $Red
}

Write-Host ""
Write-Host "To run again execute: .\run_app.ps1" -ForegroundColor $Cyan
Read-Host "Press Enter to exit"
