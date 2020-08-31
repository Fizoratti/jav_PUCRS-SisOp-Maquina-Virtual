

<h3 align="center">
  <img src="https://img.shields.io/badge/platform-windows%20%7C%20linux%20%7C%20macos-blue" />
  <img src="https://img.shields.io/badge/java-%3E%3D13.0.0-blue" />
  <img src="https://img.shields.io/badge/gradle-6.1.1-blue" />
  <img src="https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/Fizoratti/jav_PUCRS-SisOp-Maquina-Virtual" />
  <p></p>
  <p align="center">PUCRS - Escola Politécnica - 2020/2</p>
  <p align="center">Disciplina: Sistemas Operacionais</p>
  <p align="center">Prof. Fernando Luís Dotti</p>
</h3>

# Maquina Virtual

Nossa máquina virtual (MV) tem CPU e Memória.

## Trabalho Prático

###### Enunciado do trabalho
Em grupos de até 4 alunos. Construir uma máquina virtual conforme definido neste documento, em linguagem de alto nível de sua escolha. [Link para o pdf](https://moodle.pucrs.br/pluginfile.php/3351670/mod_resource/content/14/TrabalhoSO2020-2-VMv2-Fase1.pdf) que está no Moodle com o enunciado completo.

###### Primeira parte

 A máquina virtual deve poder executar programas descritos com o conjunto de instruções da CPU (seção 1.1). 
 Como parte do trabalho voce deve construir alguns programas (seção 1.4), veja o programa exemplo. 
 Em cada teste da máquina virtual, um destes programas é carregado a partir da posição 0 da memória (seção 1.2), e a CPU então é liberada para executar fazendo seu ciclo (seção 1.3).

###### Segunda parte

🚧ㅤEm breve...ㅤ🚧

## Pré Requisitos

Antes de começar, você vai precisar ter instalado o [Git](https://git-scm.com) localmente no seu computador. 
Além disto é bom ter um editor para trabalhar com o código como [VSCode](https://code.visualstudio.com/).
Usando a IDE de browser [GitPod](https://gitpod.io/) não é preciso instalar nada localmente no seu computador.

## 🏃ㅤGetting Started

> **Não é preciso instalar o gradle para rodar o código.**

```bash
# Clone este repositório
$ git clone https://github.com/Fizoratti/jav_PUCRS-SisOp-Maquina-Virtual/

# Acesse a pasta do projeto no terminal/cmd
$ cd jav_PUCRS-SisOp-Maquina-Virtual

# Execute a aplicação
$ gradle run
```

## 🌿ㅤBranches

- ```master```: Branch com a última build do projeto em que o código que executa sem erros.
- ```develop```: Branch para desenvolvimento de features.

## 🚀ㅤFeatures

### OPCODES

Conjunto de instruções.

#### J - Type Instructions

- [x] JMP
- [x] JMPI
- [x] JMPIG
- [x] JMPIL
- [x] JMPIE
- [ ] JMPIMㅤ```new```
- [ ] JMPIGMㅤ```new```
- [ ] JMPILMㅤ```new```
- [ ] JMPIEMㅤ```new```

#### I - Type Instructions

- [x] ADDI
- [ ] SUBI
- [x] ~ANDI~
- [x] ~ORI~
- [x] LDI
- [x] LDD
- [x] STD

#### R2 - Type Instructions

- [x] ADD
- [x] SUB
- [x] MULT
- [x] LDX
- [x] STX

#### R1 - Type Instructions

- [x] SWAP
- [x] STOP;

## 🛠ㅤTecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Gradle](https://gradle.org/install/)


