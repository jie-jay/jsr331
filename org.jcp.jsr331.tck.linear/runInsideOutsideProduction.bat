set CLASS_NAME=org.jcp.jsr331.linear.samples.InsideOutsideProduction

rem set SOLVER=Scip
rem set SOLVER=GLPK
set SOLVER=Coin
rem set SOLVER=lpsolve

@echo off
cd %~dp0
call .\run
pause