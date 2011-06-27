#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc
CCC=g++
CXX=g++
FC=gfortran
AS=as

# Macros
CND_PLATFORM=GNU-Linux-x86
CND_CONF=Debug
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/talk_middle.o \
	${OBJECTDIR}/reg_middle.o \
	${OBJECTDIR}/client.o \
	${OBJECTDIR}/chat_middle.o \
	${OBJECTDIR}/login_middle.o \
	${OBJECTDIR}/sockets.o


# C Compiler Flags
CFLAGS=-shared

# CC Compiler Flags
CCFLAGS=
CXXFLAGS=

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk dist/libchatDynamicLibrary.so

dist/libchatDynamicLibrary.so: ${OBJECTFILES}
	${MKDIR} -p dist
	${LINK.c} -shared -o dist/libchatDynamicLibrary.so -fPIC ${OBJECTFILES} ${LDLIBSOPTIONS} 

${OBJECTDIR}/talk_middle.o: talk_middle.c 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.c) -g -I/opt/java/include/linux -I/opt/java/include -fPIC  -MMD -MP -MF $@.d -o ${OBJECTDIR}/talk_middle.o talk_middle.c

${OBJECTDIR}/reg_middle.o: reg_middle.c 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.c) -g -I/opt/java/include/linux -I/opt/java/include -fPIC  -MMD -MP -MF $@.d -o ${OBJECTDIR}/reg_middle.o reg_middle.c

${OBJECTDIR}/client.o: client.c 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.c) -g -I/opt/java/include/linux -I/opt/java/include -fPIC  -MMD -MP -MF $@.d -o ${OBJECTDIR}/client.o client.c

${OBJECTDIR}/chat_middle.o: chat_middle.c 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.c) -g -I/opt/java/include/linux -I/opt/java/include -fPIC  -MMD -MP -MF $@.d -o ${OBJECTDIR}/chat_middle.o chat_middle.c

${OBJECTDIR}/login_middle.o: login_middle.c 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.c) -g -I/opt/java/include/linux -I/opt/java/include -fPIC  -MMD -MP -MF $@.d -o ${OBJECTDIR}/login_middle.o login_middle.c

${OBJECTDIR}/sockets.o: sockets.c 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.c) -g -I/opt/java/include/linux -I/opt/java/include -fPIC  -MMD -MP -MF $@.d -o ${OBJECTDIR}/sockets.o sockets.c

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} dist/libchatDynamicLibrary.so

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
