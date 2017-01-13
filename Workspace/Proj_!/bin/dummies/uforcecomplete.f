!***********************************************************************
!
!> \file  SIMPACK User Force/Control Element Type 30
!>
!> \author  SIMPACK AG
!>
!> \date  2014-02-05
!
!***********************************************************************



!***********************************************************************
!
!> \brief  Type-specific settings of Force/Control element type 30
!>
!> \remarks
!>  \li  Define type name (type_name)
!>  \li  Define force class fclass_force or fclass_control (spck_df_FClass)
!>  \li  Define static dimensions (spck_df_Force*Dim)
!>  \li  Optionally enable setup task -1 for par-dependent dimensions (spck_df_ForceSetupDim)
!>  \li  Optionally enable setup task 5 for default parameters (spck_df_ForceSetupDefault)
!
!***********************************************************************

      subroutine uforce30_type( str_dim    !< [in ] name string length
     +                        , type_name  !< [out] force type name
     +                        , ierr       !< [out] error code
     +                        )

#if defined(WINDOWS)
      !DEC$ ATTRIBUTES DLLEXPORT::uforce30_type
#endif

C ----------------------------------------------------------------------
C Declaration of Global Variables
C ----------------------------------------------------------------------

      implicit none

      include 'simpack.ins'  ! simpack enums

C ----------------------------------------------------------------------
C Declaration of Interface Parameters
C ----------------------------------------------------------------------

      integer, intent(in ) :: str_dim
      integer, intent(out) :: ierr

      character(len=*), intent(out) :: type_name

C ----------------------------------------------------------------------
C Declaration of Local Variables
C ----------------------------------------------------------------------

      integer :: jerr

      character(len=MESSAGE_DIM) :: message

C ----------------------------------------------------------------------
C Initialisation
C ----------------------------------------------------------------------

      ierr = 0

C ----------------------------------------------------------------------
C Execution
C ----------------------------------------------------------------------

      ! name      '123456789012345678901234567890'
      type_name = 'ZyRoLa_complete      '

      ! class
      call spck_df_FClass( fclass_force, ierr )  ! force element
      if ( ierr .ne. 0 ) goto 9001

      ! static dimensions
      call spck_df_ForceParDim(    500, ierr )     ! parameters
      if ( ierr .ne. 0 ) goto 9002

      call spck_df_ForceStDynDim(  0, ierr )     ! dynamic states
      if ( ierr .ne. 0 ) goto 9002

      call spck_df_ForceOvDim( 500, ierr )     ! output values
      if ( ierr .ne. 0 ) goto 9002

      ! enable setup task -1 for par-dependent dimensions
cc    call spck_df_ForceSetupDim( 1, ierr )
cc    if ( ierr .ne. 0 ) goto 9003

      ! enable setup task 5 for default parameters
cc    call spck_df_ForceSetupDefault( 1, ierr )
cc    if ( ierr .ne. 0 ) goto 9004

C ----------------------------------------------------------------------
C Error Handling and Return
C ----------------------------------------------------------------------

      return

 9001 write(message,*)
     +'FATAL ERROR in user Force/Control Element type 30:',lf,
     +'Cannot define force class, ierr = ',ierr,'.'
      call spck_uf_message( MSG_FATAL, message )
      ierr = 1
      return

 9002 write(message,*)
     +'FATAL ERROR in user Force/Control Element type 30:',lf,
     +'Cannot define dimensions, ierr = ',ierr,'.'
      call spck_uf_message( MSG_FATAL, message )
      ierr = 2
      return

 9003 write(message,*)
     +'FATAL ERROR in user Force/Control Element type 30:',lf,
     +'Cannot enable dimensions setup call, ierr = ',ierr,'.'
      call spck_uf_message( MSG_FATAL, message )
      ierr = 3
      return

 9004 write(message,*)
     +'FATAL ERROR in user Force/Control Element type 30:',lf,
     +'Cannot enable default parameter setup call, ierr = ',ierr,'.'
      call spck_uf_message( MSG_FATAL, message )
      ierr = 4
      return

      end subroutine



!***********************************************************************
!
!> \brief  Element-specific settings of Force/Control element type 30
!>
!> \remarks
!>  \li  Task -1: Optionally define par-dependent dimensions
!>                - Parameters:     spck_df_ForceParDim
!>                - Dynamic states: spck_df_ForceStDynDim
!>                - Output values:  spck_df_ForceOVDim
!>                - This task has to be enabled by spck_df_ForceSetupDim
!>                  in type subroutine.
!>                - Note that most Access Functions cannot be used in
!>                  this task.
!>
!>  \li  Task  5: Optionally define default parameters
!>                - Define default values for \a par for type switch and
!>                  new elements of this type.
!>                - This task has to be enabled by spck_df_ForceSetupDefault
!>                  in type subroutine.
!>                - Note that most Access Functions cannot be used in
!>                  this task.
!>
!>  \li  Task  0: Define names and types of parameters, states and output values
!>                - Parameter names \a par_name
!>                - Parameter types \a par_type
!>                - Parameter unit types \a par_unit
!>                - Dynamic state names \a stdyn_name
!>                - Dynamic state unit types \a stdyn_unit
!>                - Algebraic state names and types:   spck_df_ForceStAlg
!>                - Root state names and types:        spck_df_ForceStRoot
!>                - Descriptive state names and types: spck_df_ForceStDesc
!>                - Output value names \a ov_name
!>                - Output value unit types \a ov_unit
!>                - Note that most Access Functions cannot be used in
!>                  this task.
!>
!>  \li  Task  1: Define element-specific infos
!>                - Result type \a res_type
!>                - State reset type \a strst_type
!>                - Continuation run capability: spck_df_ForceContinueRun
!>                - Multi-threading capability: spck_df_ThreadSafe
!>                - Optionally kinematic measurements: spck_df_ForceMeas
!>                - Optionally force at Marker: spck_df_ForceAtMarkerIni
!>                - Note that parameter checks and expensive
!>                  pre-processing should be done in task 2.
!>
!>  \li  Task  2: Check parameters and perform pre-processing
!>                - Check parameters \a par
!>                - Open files
!>                - Pre-calculate internal parameters
!>                - Set internal parameters: spck_ds_ForceAddDbl, spck_ds_ForceAddInt
!>
!>  \li  Task  4: Optionally deallocate memory and close files
!
!***********************************************************************

      subroutine uforce30_setup( task        !< [in    ] |-1 | 5 | 0 | 1 | 2 | 4 | task flag
     +                         , par_dim     !< [in    ] | i | i | i | i | i | i | number of parameters
     +                         , stdyn_dim   !< [in    ] | i | i | i | i | i | i | number of dynamic states
     +                         , ov_dim      !< [in    ] | i | i | i | i | i | i | number of output values
     +                         , str_dim     !< [in    ] | i | i | i | - | - | - | max. length of names
     +                         , id          !< [in    ] | i | i | i | i | i | i | element id
     +                         , mk_from     !< [in    ] | i | i | i | i | i | i | from-marker id
     +                         , mk_to       !< [in    ] | i | i | i | i | i | i | to-marker id
     +                         , par         !< [in,out] | i |i/o| i |i/o|i/o| i | parameters
     +                         , par_type    !< [   out] | - | - | o | - | - | - | parameter types
     +                         , par_name    !< [   out] | - | - | o | - | - | - | parameter names
     +                         , par_unit    !< [   out] | - | - | o | - | - | - | parameter unit types
     +                         , stdyn_name  !< [   out] | - | - | o | - | - | - | dynamic state names
     +                         , stdyn_unit  !< [   out] | - | - | o | - | - | - | dynamic state unit types
     +                         , ov_name     !< [   out] | - | - | o | - | - | - | output value names
     +                         , ov_unit     !< [   out] | - | - | o | - | - | - | output value unit types
     +                         , res_type    !< [   out] | - | - | - | o | - | - | output flag
     +                         , stdyn_nr    !< [      ] | - | - | - | - | - | - | (obsolete)
     +                         , stroot_nr   !< [      ] | - | - | - | - | - | - | (obsolete)
     +                         , strst_type  !< [   out] | - | - | - | o | - | - | state reset flag
     +                         , ierr        !< [   out] | o | o | o | o | o | o | error code
     +                         )

#if defined(WINDOWS)
      !DEC$ ATTRIBUTES DLLEXPORT::uforce30_setup
#endif

C ----------------------------------------------------------------------
C Declaration of Global Variables
C ----------------------------------------------------------------------

      implicit none

      include 'simpack.ins'  ! simpack enums

C ----------------------------------------------------------------------
C Declaration of Interface Parameters
C ----------------------------------------------------------------------

      integer, intent(in ) :: task
      integer, intent(in ) :: par_dim
      integer, intent(in ) :: stdyn_dim
      integer, intent(in ) :: ov_dim
      integer, intent(in ) :: str_dim
      integer, intent(in ) :: id
      integer, intent(in ) :: mk_from
      integer, intent(in ) :: mk_to
      integer, intent(out) :: par_type(par_dim)
      integer, intent(out) :: par_unit(par_dim)
      integer, intent(out) :: stdyn_unit(stdyn_dim)
      integer, intent(out) :: ov_unit(ov_dim)
      integer, intent(out) :: res_type
      integer              :: stdyn_nr
      integer              :: stroot_nr
      integer, intent(out) :: strst_type
      integer, intent(out) :: ierr

      double precision, intent(inout) :: par(par_dim)

      character(len=*), intent(out) :: par_name(par_dim)
      character(len=*), intent(out) :: stdyn_name(stdyn_dim)
      character(len=*), intent(out) :: ov_name(ov_dim)

C ----------------------------------------------------------------------
C Declaration of Local Variables
C ----------------------------------------------------------------------

      integer :: nov
      integer :: jerr
      integer :: npar
      integer :: lname
      integer :: nstdyn

      character(len=ELEMNAME_DIM) :: name
      character(len=MESSAGE_DIM)  :: message

C ----------------------------------------------------------------------
C Initialisation
C ----------------------------------------------------------------------

      ierr = 0

C ----------------------------------------------------------------------
C Execution
C ----------------------------------------------------------------------

      ! dimensions
      npar   = 500
      nstdyn = 0
      nov    = 500

C ----------------------------------------------------------------------
C task = -1 : Parameter-dependent Dimensions
C ----------------------------------------------------------------------

      if ( task .eq. -1 ) then

         ! parameters
cc       call spck_df_ForceParDim( npar, ierr )
cc       if ( ierr .ne. 0 ) goto 9001

         ! dynamic states
cc       call spck_df_ForceStDynDim( nstdyn, ierr )
cc       if ( ierr .ne. 0 ) goto 9001

         ! output values
cc       call spck_df_ForceOVDim( nov, ierr )
cc       if ( ierr .ne. 0 ) goto 9001

         return

C ----------------------------------------------------------------------
C task = 0 : Names and Types
C ----------------------------------------------------------------------

      else if ( task .eq. 0 ) then

         ! initialise outputs
		 par_name(1:par_dim) = ' '
         par_type(1:par_dim) = knodef
         par_unit(1:par_dim) = knodef
         stdyn_name(1:stdyn_dim) = ' '
         stdyn_unit(1:stdyn_dim) = knodef
         ov_name(1:ov_dim) = ' '
         ov_unit(1:ov_dim) = knodef

         ! check array dimensions
         if ( par_dim   .ne. npar   ) goto 9002
         if ( stdyn_dim .ne. nstdyn ) goto 9002
         if ( ov_dim    .ne. nov    ) goto 9002

C ----------------------------------------------------------------------
C ---------------------Parameter WK-LB-Kontakt
C ----------------------------------------------------------------------
         
         !         name '123456789012345678901234567890'  |  type                          |  unit type
		 ! ##### Marker #####
		 par_name( 1) = 'Marker Aussenring             '  ;  par_type( 1) = knr_marker		;  par_unit( 1) = knodef
		 par_name( 2) = 'Marker Innenring              '  ;  par_type( 2) = knr_marker		;  par_unit( 2) = knodef
		 par_name( 3) = 'Marker Waelzkoerper           '  ;  par_type( 3) = knr_marker		;  par_unit( 3) = knodef
		 par_name( 4) = 'Marker Waelzkoerper aussen    '  ;  par_type( 4) = knr_marker		;  par_unit( 4) = knodef
		 par_name( 5) = 'Marker Ground                 '  ;  par_type( 5) = knr_marker		;  par_unit( 5) = knodef
		 
		 ! ##### Parameter #####
		 par_name( 6) = 'Kontaktlocation               '  ;  par_type( 6) = knr_integer		;  par_unit( 6) = knodef
		 
		 ! ##### Geometrie Parameter (Aussenring/Innenring) #####
		 par_name( 7) = 'AR Laufbahndurchmesser        '  ;  par_type( 7) = knr_double     	;  par_unit( 7) = knodef
		 par_name( 8) = 'AR Profilradius               '  ;  par_type( 8) = knr_double     	;  par_unit( 8) = knodef
		 par_name( 9) = 'IR Laufbahndurchmesser        '  ;  par_type( 9) = knr_double     	;  par_unit( 9) = knodef
		 par_name(10) = 'IR Profilradius               '  ;  par_type(10) = knr_double     	;  par_unit(10) = knodef
		 
		 ! ##### Geometrie Parameter Waelzkoerper #####
		 par_name(11) = 'WK Länge                      '  ;  par_type(11) = knr_double     	;  par_unit(11) = knodef
		 par_name(12) = 'WK Durchmesser                '  ;  par_type(12) = knr_double     	;  par_unit(12) = knodef
		 par_name(13) = 'WK Profilradius               '  ;  par_type(13) = knr_double     	;  par_unit(13) = knodef
		 par_name(14) = 'Profil Type                   '  ;  par_type(14) = knr_integer		;  par_unit(14) = knodef
		 par_name(15) = 'WK-profil nach DIN-ISO        '  ;  par_type(15) = knr_double     	;  par_unit(15) = knodef
		 par_name(16) = 'WK-effektive Länge            '  ;  par_type(16) = knr_double     	;  par_unit(16) = knodef
		 par_name(17) = 'WK-Länge Zylinderstück        '  ;  par_type(17) = knr_double     	;  par_unit(17) = knodef
		 par_name(18) = 'WK-Beginn Geradenstück        '  ;  par_type(18) = knr_double     	;  par_unit(18) = knodef
		 par_name(19) = 'WK-profil Kantenradius        '  ;  par_type(19) = knr_double     	;  par_unit(19) = knodef
		 
		 ! Vektor mit Materialdaten
		 par_name(20) = 'IR_E                          '  ;  par_type(20) = knr_double     	;  par_unit(20) = knodef
		 par_name(21) = 'IR_v                          '  ;  par_type(21) = knr_double     	;  par_unit(21) = knodef
		 par_name(22) = 'AR_E                          '  ;  par_type(22) = knr_double     	;  par_unit(22) = knodef
		 par_name(23) = 'AR_v                          '  ;  par_type(23) = knr_double     	;  par_unit(23) = knodef
		 par_name(24) = 'WK_E                          '  ;  par_type(24) = knr_double     	;  par_unit(24) = knodef
		 par_name(25) = 'WK_v                          '  ;  par_type(25) = knr_double     	;  par_unit(25) = knodef
	 
		 ! ##### Reibwert- und Schmierstoffdaten #####
		  par_name(26) = 'Festkörperreibmodell         '  ;  par_type(26) = knr_integer		;  par_unit(26) = knodef
		  par_name(27) = 'EHD-Reibung  0-aus 1-an      '  ;  par_type(27) = knr_integer		;  par_unit(27) = knodef
		  par_name(28) = 'Schmierstoff - race_lub_mod  '  ;  par_type(28) = knr_integer		;  par_unit(28) = knodef
		  par_name(29) = 'Schubspannung - tau_mod      '  ;  par_type(29) = knr_integer		;  par_unit(29) = knodef
		  par_name(30) = 'Ther_Korr-faktor - filmT_mod '  ;  par_type(30) = knr_integer		;  par_unit(30) = knodef
		  par_name(31) = 'Erwärmung - comprT_mod       '  ;  par_type(31) = knr_integer		;  par_unit(31) = knodef
		  par_name(32) = 'vel_s                        '  ;  par_type(32) = knr_double     	;  par_unit(32) = knodef
		  par_name(33) = 'vel_d                        '  ;  par_type(33) = knr_double     	;  par_unit(33) = knodef
		  par_name(34) = 'mu_s                         '  ;  par_type(34) = knr_double     	;  par_unit(34) = knodef
		  par_name(35) = 'mu_d                         '  ;  par_type(35) = knr_double     	;  par_unit(35) = knodef
		  par_name(36) = 'lubtmp                       '  ;  par_type(36) = knr_double     	;  par_unit(36) = knodef
		  par_name(37) = 'etaZero                      '  ;  par_type(37) = knr_double     	;  par_unit(37) = knodef
		  par_name(38) = 'alphaT                       '  ;  par_type(38) = knr_double     	;  par_unit(38) = knodef
		  par_name(39) = 'alphaP                       '  ;  par_type(39) = knr_double     	;  par_unit(39) = knodef
		  par_name(40) = 'lambdaZero                   '  ;  par_type(40) = knr_double     	;  par_unit(40) = knodef
		  par_name(41) = 'alphaLambda                  '  ;  par_type(41) = knr_double     	;  par_unit(41) = knodef		  
		  par_name(42) = 'C1                           '  ;  par_type(42) = knr_double     	;  par_unit(42) = knodef
		  par_name(43) = 'C2                           '  ;  par_type(43) = knr_double     	;  par_unit(43) = knodef
		  par_name(44) = 'AV                           '  ;  par_type(44) = knr_double     	;  par_unit(44) = knodef
		  par_name(45) = 'Bv                           '  ;  par_type(45) = knr_double     	;  par_unit(45) = knodef
		  par_name(46) = 'CV                           '  ;  par_type(46) = knr_double     	;  par_unit(46) = knodef
		  par_name(47) = 'DR                           '  ;  par_type(47) = knr_double     	;  par_unit(47) = knodef
		  par_name(48) = 'ER                           '  ;  par_type(48) = knr_double     	;  par_unit(48) = knodef
		  par_name(49) = 'pR                           '  ;  par_type(49) = knr_double     	;  par_unit(49) = knodef
		  par_name(50) = 'B                            '  ;  par_type(50) = knr_double     	;  par_unit(50) = knodef
		  par_name(51) = 'C                            '  ;  par_type(51) = knr_double     	;  par_unit(51) = knodef
		  par_name(52) = 'K                            '  ;  par_type(52) = knr_double     	;  par_unit(52) = knodef
		  par_name(53) = 'a1                           '  ;  par_type(53) = knr_double     	;  par_unit(53) = knodef
		  par_name(54) = 'a2                           '  ;  par_type(54) = knr_double     	;  par_unit(54) = knodef
		  par_name(55) = 'b1                           '  ;  par_type(55) = knr_double     	;  par_unit(55) = knodef
		  par_name(56) = 'b2                           '  ;  par_type(56) = knr_double     	;  par_unit(56) = knodef
		  par_name(57) = 'rhoZero                      '  ;  par_type(57) = knr_double     	;  par_unit(57) = knodef
		  par_name(58) = 'tauRheo                      '  ;  par_type(58) = knr_double     	;  par_unit(58) = knodef
		  par_name(59) = 'sigma                        '  ;  par_type(59) = knr_double     	;  par_unit(59) = knodef
		  par_name(60) = 'BZH                          '  ;  par_type(60) = knr_double     	;  par_unit(60) = knodef
		  par_name(61) = 'CZH                          '  ;  par_type(61) = knr_double     	;  par_unit(61) = knodef
		  par_name(62) = 'alphaHys                     '  ;  par_type(62) = knr_double     	;  par_unit(62) = knodef
		  par_name(63) = 'scaleHys                     '  ;  par_type(63) = knr_double     	;  par_unit(63) = knodef
		  par_name(64) = 'cr                           '  ;  par_type(64) = knr_double     	;  par_unit(64) = knodef
		  par_name(65) = 'rexp                         '  ;  par_type(65) = knr_double     	;  par_unit(65) = knodef
		  
		  ! ##### Dämpfungs parameter #####
		  par_name(66) = 'd_mod                        '  ;  par_type(66) = knr_integer		;  par_unit(66) = knodef
		  par_name(67) = 'p_t                          '  ;  par_type(67) = knr_double     	;  par_unit(67) = knodef
		  par_name(68) = 'd_max                        '  ;  par_type(68) = knr_double     	;  par_unit(68) = knodef
		  par_name(69) = 'K0                           '  ;  par_type(69) = knr_double     	;  par_unit(69) = knodef
		  par_name(70) = 'KR                           '  ;  par_type(70) = knr_double     	;  par_unit(70) = knodef
		  par_name(71) = 'KL                           '  ;  par_type(71) = knr_double     	;  par_unit(71) = knodef
		  par_name(72) = 'KE                           '  ;  par_type(72) = knr_double     	;  par_unit(72) = knodef
		  par_name(73) = 'Keta                         '  ;  par_type(73) = knr_double     	;  par_unit(73) = knodef
		  par_name(74) = 'Kalpha                       '  ;  par_type(74) = knr_double     	;  par_unit(74) = knodef
		  par_name(75) = 'Kq                           '  ;  par_type(75) = knr_double     	;  par_unit(75) = knodef
		  par_name(76) = 'Ku                           '  ;  par_type(76) = knr_double     	;  par_unit(76) = knodef
		  par_name(77) = 'Kf                           '  ;  par_type(77) = knr_double     	;  par_unit(77) = knodef
		  par_name(78) = 'fe                           '  ;  par_type(78) = knr_double     	;  par_unit(78) = knodef
		  
		  ! ###### Parametern für Scheibenmodell, Gauss-Integration, Curvefit #####
		  par_name(79) = 'ctctype_LB                   '  ;  par_type(79) = knr_integer		;  par_unit(79) = knodef
		  par_name(80) = 'no_gauss_LB                  '  ;  par_type(80) = knr_integer		;  par_unit(80) = knodef
		  par_name(81) = 'cf_pnts_LB                   '  ;  par_type(81) = knr_integer		;  par_unit(81) = knodef
		  par_name(82) = 'cf_p_max_LB                  '  ;  par_type(82) = knr_double     	;  par_unit(82) = knodef
		  
		  ! ###### GForce-IDs für Ausgabe von Ergebnissen in PostProcessor/Datei ######
		  par_name(83) = 'gfo_id_AR                    '  ;  par_type(83) = knr_integer		;  par_unit(83) = knodef
		  par_name(84) = 'gfo_id_IR                    '  ;  par_type(84) = knr_integer		;  par_unit(84) = knodef
		  par_name(85) = 'AR_Dicke                     '  ;  par_type(85) = knr_double     	;  par_unit(85) = knodef
		  
		  ! ##### Flexibler Aussenring #####				  
		  par_name(86) = 'Flexib. AR 0-aus 1-an        '  ;  par_type(86) = knr_integer    ;  par_unit(86) = knodef
		  par_name(87) = 'Flexib. AR: Kraft aus FE     '  ;  par_type(87) = knr_double     ;  par_unit(87) = knodef
		  
		  		  ! ##### ASCII Ausgabe #####				  
		  par_name(88) = 'ASCII-Ausgabe 0-aus 1-EL 2-SL'  ;  par_type(86) = knr_integer    ;  par_unit(86) = knodef
		

C ----------------------------------------------------------------------
C ---------------------Parameter Bordkräfte (ehem. uforce23) -----------
C ----------------------------------------------------------------------


		
		         ! ##### Marker #####
		 par_name(89) = 'Marker Laufbahn Aussen        '  ;  par_type(89)  = knr_marker	 ;  par_unit(89) = knodef
		                                                                                       
		                                                             
		 ! ##### Geometrie Parameter Wälzkörper #####                
		 par_name(91) = 'Radius des Wälzkörpers        '  ;  par_type(91)  = knr_double    ;  par_unit(91) = knodef
		 par_name(92) = 'Kantenradius des Wälzkörpers  '  ;  par_type(92)  = knr_double    ;  par_unit(92) = knodef
		                                                                                              
		 ! ##### Geometrievektor  (Außen- und Innenring) #####                                        
		 par_name(93) = 'Breite Laufbahn Außenring     '  ;  par_type(93)  = knr_double    ;  par_unit(93) = knodef
		 par_name(94) = 'Bord-durch Außen-Außenring    '  ;  par_type(94)  = knr_double    ;  par_unit(94) = knodef
		 par_name(95) = 'Bord-durch Innen-Außenring    '  ;  par_type(95)  = knr_double    ;  par_unit(95) = knodef
		 par_name(96) = 'Bord Dicke-Außenring          '  ;  par_type(96)  = knr_double    ;  par_unit(96) = knodef
		                                                                                              
		 par_name(97) = 'Breite Laufbahn Innenring     '  ;  par_type(97)  = knr_double    ;  par_unit(97) = knodef
		 par_name(98) = 'Bord-durch Außen-Innenring    '  ;  par_type(98)  = knr_double    ;  par_unit(98) = knodef
		 par_name(99) = 'Bord-durch Innen-Innenring    '  ;  par_type(99)  = knr_double    ;  par_unit(99) = knodef
		 par_name(100) = 'Bord Dicke-Innenring          '  ;  par_type(100) = knr_double   ;   par_unit(100) = knodef
		                                                                                               
		 par_name(101) = 'Bordoeffnungswinkel IR        '  ;  par_type(101) = knr_double    ;  par_unit(101) = knodef
		 par_name(102) = 'Bordoeffnungswinkel AR        '  ;  par_type(102) = knr_double    ;  par_unit(102) = knodef
		 par_name(103) = 'Teilkreisradius               '  ;  par_type(103) = knr_double    ;  par_unit(103) = knodef
		                                                                                               
		                                                                                               
		 ! ##### Reibwert- daten #####                                                                 
		 par_name(104) = 'Festkörperreibmodell Bord     '  ;  par_type(104) = knr_integer   ;  par_unit(104) = knodef
		 par_name(105) = 'vel_s_BRD                     '  ;  par_type(105) = knr_double    ;  par_unit(105) = knodef
		 par_name(106) = 'vel_d_BRD                     '  ;  par_type(106) = knr_double    ;  par_unit(106) = knodef
		 par_name(107) = 'mu_s_BRD                      '  ;  par_type(107) = knr_double    ;  par_unit(107) = knodef
		 par_name(108) = 'mu_d_BRD                      '  ;  par_type(108) = knr_double    ;  par_unit(108) = knodef
		 	                                                                                           
		 ! ##### Vektor mit Dämpfungsdaten #####                                                       
		 par_name(109) = 'Durchdringung-max.dämpfungBRD '  ;  par_type(109) = knr_double    ;  par_unit(109) = knodef
		 par_name(110) = 'Maximale Dämpfung BRD         '  ;  par_type(110) = knr_double    ;  par_unit(110) = knodef	 
		 par_name(111) = 'Marker_Käfig_Mittel BRD       '  ;  par_type(111) = knr_marker	;  par_unit(111) = knodef
                                                                      
		                                                              
		 ! ##### Zusätzliohe Informationen für die Simulation #####   
		                                                              
		 par_name(112) = 'Anzahl Bordkontakte          '  ;  par_type(112) = knr_integer    ;  par_unit(112) = knodef  
		 
		 
		 
C ----------------------------------------------------------------------
C ---------------------Parameter Taschenfederkäfig (ehem. uforce24) ----
C ----------------------------------------------------------------------	 
		 
		 
		 
		 par_name(113) = 'Marker Käfig Tasche           '  ;  par_type(113) = knr_marker		;  par_unit(113) = knodef
		 
		 par_name(114) = 'Tangential Taschenspiel       '  ;  par_type(114) = knr_double     	;  par_unit(114) = knodef
		 par_name(115) = 'Radial Taschenspiel           '  ;  par_type(115) = knr_double     	;  par_unit(115) = knodef
		 par_name(116) = 'Axial Taschenspiel            '  ;  par_type(116) = knr_double     	;  par_unit(116) = knodef


C ----------------------------------------------------------------------
C ---------------------Parameter Kontaktkäfig (ehem. uforce25) ----
C ----------------------------------------------------------------------	


		 ! ##### Marker #####

		 par_name(117) = 'Marker Käfig Haupt                 '  ;  par_type(117) = knr_marker		;  par_unit(117) = knodef
		 par_name(118) = 'Marker Käfig aussen           '  ;  par_type(118) = knr_marker		;  par_unit(118) = knodef
	 	
		! ##### Geometrie Parameter (Käfig) #####
		 par_name(119) = 'Käfig Taschendurchmesser      '  ;  par_type(119) = knr_double     	;  par_unit(119) = knodef
		 par_name(120) = 'Dicke des Steges              '  ;  par_type(120) = knr_double     	;  par_unit(120) = knodef
		

		  ! Vektor mit Materialdaten
		 par_name(121) = 'Elastizitätsmodul Käfigs      '  ;  par_type(121) = knr_double     	;  par_unit(121) = knodef
		 par_name(122) = 'Querkontraktionszahl Käfig    '  ;  par_type(122) = knr_double     	;  par_unit(122) = knodef
		 
		 ! ##### Reibwert- und Schmierstoffdaten #####
		 par_name(123) = 'Festkörperreibmodell KF      '  ;  par_type(123) = knr_integer		;  par_unit(123) = knodef
		 par_name(124) = 'vel_s KF                     '  ;  par_type(124) = knr_double     	;  par_unit(124) = knodef
		 par_name(125) = 'vel_d KF                     '  ;  par_type(125) = knr_double     	;  par_unit(125) = knodef
		 par_name(126) = 'mu_s  KF                     '  ;  par_type(126) = knr_double     	;  par_unit(126) = knodef
		 par_name(127) = 'mu_d  KF                     '  ;  par_type(127) = knr_double     	;  par_unit(127) = knodef		 
		 
		 ! ###### Parametern für Scheibenmodell, Gauss-Integration, Curvefit #####
		 par_name(128) = 'Kontakt Modell KF_WK         '  ;  par_type(128) = knr_integer		;  par_unit(128) = knodef
		 par_name(129) = 'Anzahl der Curvefit-Punkte KF'  ;  par_type(129) = knr_integer		;  par_unit(129) = knodef
		 par_name(130) = 'P_Max für Curvefit KF        '  ;  par_type(130) = knr_double     	;  par_unit(130) = knodef
		 
		 ! ##### Dämpfungs parameter #####
		 par_name(131) = 'd_mod  KF                    '  ;  par_type(131) = knr_integer		;  par_unit(131) = knodef
		 par_name(132) = 'p_t    KF                    '  ;  par_type(132) = knr_double     	;  par_unit(132) = knodef
		 par_name(133) = 'd_max  KF                    '  ;  par_type(133) = knr_double     	;  par_unit(133) = knodef
		
C ----------------------------------------------------------------------
C ---------------------Parameter Führungsbord (ehem. uforce26) ----
C ---------------------------------------------------------------------


		 par_name(134) = 'Marker Käfig Bord             '  ;  par_type(134) = knr_marker		;  par_unit(134) = knodef
		 par_name(135) = 'Marker Laufbahnbord           '  ;  par_type(135) = knr_marker		;  par_unit(135) = knodef
		 par_name(136) = 'Käfigmod                      '  ;  par_type(136) = knr_integer		;  par_unit(136) = knodef		
		 par_name(137) = 'Aussendurchmesser Käfig       '  ;  par_type(137) = knr_double		;  par_unit(137) = knodef
		 par_name(138) = 'Innendurchmesser Käfig        '  ;  par_type(138) = knr_double		;  par_unit(138) = knodef
		  
		  
		 
		 ! output values
			! -------------
		!	!      name  '123456789012345678901234567890'   |  unit type
	    	ov_name( 1) = 'Nomralkraft X                '   ;  ov_unit( 1) = kp_force
	    	ov_name( 2) = 'Nomralkraft Y                '   ;  ov_unit( 2) = kp_force
	    	ov_name( 3) = 'Nomralkraft Z                '   ;  ov_unit( 3) = kp_force
	    	ov_name( 4) = 'Nomralkraft ABS              '   ;  ov_unit( 4) = kp_force
	    	ov_name( 5) = 'Daempfungskraft X            '   ;  ov_unit( 5) = kp_force
	    	ov_name( 6) = 'Daempfungskraft Y            '   ;  ov_unit( 6) = kp_force
	    	ov_name( 7) = 'Daempfungskraft Z            '   ;  ov_unit( 7) = kp_force
	    	ov_name( 8) = 'Daempfungskraft ABS          '   ;  ov_unit( 8) = kp_force
	    	ov_name( 9) = 'Reibkraft X                  '   ;  ov_unit( 9) = kp_force
	    	ov_name(10) = 'Reibkraft Y                  '   ;  ov_unit(10) = kp_force
	    	ov_name(11) = 'Reibkraft Z                  '   ;  ov_unit(11) = kp_force
	    	ov_name(12) = 'Reibkraft ABS                '   ;  ov_unit(12) = kp_force
	    	ov_name(13) = 'Reibmoment X                 '   ;  ov_unit(13) = kp_torque
	    	ov_name(14) = 'Reibmoment Y                 '   ;  ov_unit(14) = kp_torque
	    	ov_name(15) = 'Reibmoment Z                 '   ;  ov_unit(15) = kp_torque
	    	ov_name(16) = 'Reibmoment ABS               '   ;  ov_unit(16) = kp_torque
	!	!	                                            
			ov_name(17) = 'Schmierfilmhoehe [mum]       '   ;  ov_unit(17) = knodef
		!	ov_name(18) = 'Durchdringung [mm]           '   ;  ov_unit(18) = knodef
		!	ov_name(19) = 'Hertsche Pressung [MPa]      '   ;  ov_unit(19) = knodef

		 
		 

C ----------------------------------------------------------------------
C task = 1 : Element-specific Infos
C ----------------------------------------------------------------------

      else if ( task .eq. 1 ) then

         ! result type
         ! kforce_res_none         = no force, no torque
         ! kforce_res_force        = force only
         ! kforce_res_torque       = torque only
         ! kforce_res_force_torque = force and torque
         res_type = kforce_res_force_torque

         ! state reset type (see also spck_slv_StRstInit)
         ! kforce_strst_none     = state reset disabled
         ! kforce_strst_continue = state reset enabled, do not calculate consistent states
         ! kforce_strst_assemble = state reset enabled, calculate consistent states
         strst_type = kforce_strst_none

         ! continuation run capability
cc       call spck_df_ForceContinueRun( 0, id, ierr )
cc       if ( ierr .ne. 0 ) goto 9007

         ! multi-threading capability
cc       call spck_df_ThreadSafe( 0, ierr )
cc       if ( ierr .ne. 0 ) goto 9008

C ----------------------------------------------------------------------
C task = 2 : Check Parameters and Pre-Processing
C ----------------------------------------------------------------------

      else if ( task .eq. 2 ) then

         continue

C ----------------------------------------------------------------------
C task = 4 : Final Call
C ----------------------------------------------------------------------

      else if ( task .eq. 4 ) then

         continue

C ----------------------------------------------------------------------
C task = 5 : Default Parameters
C ----------------------------------------------------------------------

      else if ( task .eq. 5 ) then

         continue

      end if

C ----------------------------------------------------------------------
C Error Handling and Return
C ----------------------------------------------------------------------

      return

 9001 call spck_an_ForceID2Name( name, lname, id, jerr )
      write(message,*)
     +'FATAL ERROR in pre-processing of user Force/Control Element',lf,
     + trim(name),':',lf,
     +'Cannot define dimensions, ierr = ',ierr,'.'
      call spck_uf_message( MSG_FATAL, message )
      ierr = 1
      return

 9002 call spck_an_ForceID2Name( name, lname, id, jerr )
      write(message,*)
     +'FATAL ERROR in pre-processing of user Force/Control Element',lf,
     + trim(name),':',lf,
     +'Array dimensions are not sufficient.'
      call spck_uf_message( MSG_FATAL, message )
      ierr = 2
      return

 9003 call spck_an_ForceID2Name( name, lname, id, jerr )
      write(message,*)
     +'FATAL ERROR in pre-processing of user Force/Control Element',lf,
     + trim(name),':',lf,
     +'Cannot define selection menu, ierr = ',ierr,'.'
      call spck_uf_message( MSG_FATAL, message )
      ierr = 3
      return

 9004 call spck_an_ForceID2Name( name, lname, id, jerr )
      write(message,*)
     +'FATAL ERROR in pre-processing of user Force/Control Element',lf,
     + trim(name),':',lf,
     +'Cannot define algebraic state, ierr = ',ierr,'.'
      call spck_uf_message( MSG_FATAL, message )
      ierr = 4
      return

 9005 call spck_an_ForceID2Name( name, lname, id, jerr )
      write(message,*)
     +'FATAL ERROR in pre-processing of user Force/Control Element',lf,
     + trim(name),':',lf,
     +'Cannot define root state, ierr = ',ierr,'.'
      call spck_uf_message( MSG_FATAL, message )
      ierr = 5
      return

 9006 call spck_an_ForceID2Name( name, lname, id, jerr )
      write(message,*)
     +'FATAL ERROR in pre-processing of user Force/Control Element',lf,
     + trim(name),':',lf,
     +'Cannot define descriptive state, ierr = ',ierr,'.'
      call spck_uf_message( MSG_FATAL, message )
      ierr = 6
      return

 9007 call spck_an_ForceID2Name( name, lname, id, jerr )
      write(message,*)
     +'FATAL ERROR in pre-processing of user Force/Control Element',lf,
     + trim(name),':',lf,
     +'Cannot define continuation run capability, ierr = ',ierr,'.'
      call spck_uf_message( MSG_FATAL, message )
      ierr = 7
      return

 9008 call spck_an_ForceID2Name( name, lname, id, jerr )
      write(message,*)
     +'FATAL ERROR in pre-processing of user Force/Control Element',lf,
     + trim(name),':',lf,
     +'Cannot define multi-threading capability, ierr = ',ierr,'.'
      call spck_uf_message( MSG_FATAL, message )
      ierr = 8
      return

      end subroutine



!***********************************************************************
!
!> \brief  Evaluation of Force/Control element type 30
!>
!> \remarks
!>  \li  Task  0: Determine force, torque and output values
!>  \li  Task  1: Evaluate root functions
!>  \li  Task  2: Perform state reset after root state switch
!>  \li  Task  3: Determine algebraic state residuals
!>  \li  Task  4: Initialise states after calculation of consistent states
!>  \li  Task  5: Initialisation (first call)
!>  \li  Task  6: Finalisation (last call)
!>  \li  \a force and \a torque have to be returned in coordinates of
!>       the from-body reference frame Ri.
!>  \li  \a force and \a torque are applied at the from-marker Mi
!>       and with reversed sign at the to-marker Mj. In addition a
!>       reaction torque r_MiMj x \a force is applied at Mi by the
!>       solver.
!
!***********************************************************************

      subroutine uforce30( task        !< [in    ] | 0 | 1 | 2 | 3 | 4 | 5 | 6 | task flag
     +                   , par_dim     !< [in    ] | i | i | i | i | i | i | i | number of parameters
     +                   , uin_dim     !< [in    ] | i | i | i | i | i | i | i | number of u-vector components
     +                   , stdyn_dim   !< [in    ] | i | i | i | i | i | i | i | number of dynamic states
     +                   , stroot_dim  !< [in    ] | i | i | i | i | i | i | i | number of root states
     +                   , ov_dim      !< [in    ] | i | i | i | i | i | i | i | number of output values
     +                   , id          !< [in    ] | i | i | i | i | i | i | i | element id
     +                   , par         !< [in    ] | i | i | i | i | i | i | i | parameters
     +                   , mk_from     !< [in    ] | i | i | i | i | i | i | i | from-marker id
     +                   , mk_to       !< [in    ] | i | i | i | i | i | i | i | to-marker id
     +                   , time        !< [in    ] | i | i | i | i | i | i | i | time
     +                   , uin         !< [in    ] | i | i | i | i | i | i | i | u-vector (deprecated, use access function spck_as_UEle)
     +                   , stdyn       !< [in,out] | i | i | i | i |i/o| i | i | dynamic states
     +                   , stroot      !< [in,out] |i/o| i | i | i |i/o| i | i | root states
     +                   , stdynd      !< [   out] | o | - | - | - | - | - | - | dynamic state derivatives
     +                   , force       !< [   out] | o | - | - | - | - | - | - | force vector acting at from-marker w.r.t. from-brf
     +                   , torque      !< [   out] | o | - | - | - | - | - | - | torque vector acting at from-marker w.r.t. from-brf
     +                   , ov          !< [   out] | o | - | - | - | - | - | - | output values
     +                   , valroot     !< [   out] | - | o | - | - | - | - | - | root function values
     +                   , ierr        !< [   out] | o | o | o | o | o | o | o | error code
     +                   )
	 
#if defined(WINDOWS)
      !DEC$ ATTRIBUTES DLLEXPORT::uforce30
#endif

C ----------------------------------------------------------------------
C Declaration of Global Variables
C ----------------------------------------------------------------------

      implicit none
	  
      include 'simpack.ins'  ! simpack enums

C ----------------------------------------------------------------------
C Declaration of Interface Parameters
C ----------------------------------------------------------------------

      integer, intent(in   ) :: task
      integer, intent(in   ) :: par_dim
      integer, intent(in   ) :: uin_dim
      integer, intent(in   ) :: stdyn_dim
      integer, intent(in   ) :: stroot_dim
      integer, intent(in   ) :: ov_dim
      integer, intent(in   ) :: id
      integer, intent(in   ) :: mk_from
      integer, intent(in   ) :: mk_to
      integer, intent(inout) :: stroot(stroot_dim)
      integer, intent(  out) :: ierr

      double precision, intent(in   ) :: par(par_dim)
      double precision, intent(in   ) :: time
      double precision, intent(in   ) :: uin(uin_dim)
      double precision, intent(inout) :: stdyn(stdyn_dim)
      double precision, intent(  out) :: stdynd(stdyn_dim)
      double precision, intent(  out) :: force(3)
      double precision, intent(  out) :: torque(3)
      double precision, intent(  out) :: ov(ov_dim)
      double precision, intent(  out) :: valroot(stroot_dim)
	  
C ----------------------------------------------------------------------
C Declaration of Local Variables
C ----------------------------------------------------------------------
  
      INTEGER 			    :: meas_flg
      LOGICAL, PARAMETER	:: iflag =.true.
      INTEGER				:: err
      INTEGER				:: i
      DOUBLE PRECISION		:: pi
      INTEGER, PARAMETER 	:: NC_NAME = 200
      INTEGER, PARAMETER 	:: NC_MESSAGE = 2048
	  
	  LOGICAL				:: dflag
      
      character(len=100) :: str
C ----------------------------------------------------------------------
C notwendige Variablen zur automatisierten Ausgabe
C ----------------------------------------------------------------------	  
	     
	     LOGICAL 			:: ordnerabfrage
	     CHARACTER(len=NC_MESSAGE) :: message
		
	     !INTEGER 	 		:: hilfs_iflag
	     INTEGER, save 		:: hilfs_iflag 			! Die Erstellung der hilfs_iflag Variable ist notwendig um einmalige Aktionen auszuführen
	     data hilfs_iflag/1/ 					    ! Ein data-statement wird nur einmalig ausgeführt
		
	     INTEGER, save 		:: lager_iflag, lagernr
	     data lager_iflag/1/
		
	     INTEGER 			:: i_wk
	     INTEGER 			:: date_time(8)
	     INTEGER, save 		:: ausgabeindex, ausgabe
	     INTEGER, save 		:: anz_parallelSim_int
	     INTEGER, save 		:: dateinr, modelltyp, l_ausgabepfad
	     INTEGER, save 		:: force_element_name_length, marker_element_name_length
		
		! ##### Auslesefelder aus Simpack #####
	     DOUBLE PRECISION	::   		tdisp311(3), tdisp322(3)
	     DOUBLE PRECISION	::   		tdisp315(3), tdisp325(3)
	     DOUBLE PRECISION	::   		tdisp_WK_LB_ground(3)
	     DOUBLE PRECISION	::   		TVEL_WK_LB(3), TVEL_LB_ground(3)
	     DOUBLE PRECISION	::   		RVEL_WK_LB(3), RVEL_LB_ground(3), rvel_wk_ground(3)
	     DOUBLE PRECISION	::   		TRANSFORM_zeta(3,3), TRANSFORM_psi(3,3)
	     DOUBLE PRECISION 	::   		tdisp155(3), tdisp255(3), tdisp455(3), tdisp311_ausgabe(3)		! Verlagerung bzgl. GR von AR,IR,KF
	     DOUBLE PRECISION 	::   		rdisp355(3), rdisp455(3), rdisp155(3), rdisp255(3), rdisp345(3) ! Rotationsgeschw. bzgl. GR von AR,IR,KF
	     DOUBLE PRECISION 	::	  		verschiebung211(3),verkippung211(3),kraft255(3),moment255(3)
	     DOUBLE PRECISION 	::   		kraft155(3),moment155(3),kraft455(3),moment455(3)
	     CHARACTER(len=100), save ::   lager_vektor(10)  ! max. 10 Lager pro System (Annahme)
		
		! ##### Ausgabevariablen #####
	     CHARACTER(len=10) 		  :: date_time_b(3)
	     CHARACTER(len=300), save :: ausgabepfad, ausgabepfad_anfang, ausgabepfad_ende, atemp
	     CHARACTER(len=10),  save :: datum, uhrzeit	
	     CHARACTER(len=300), save :: path, folder
	     CHARACTER(len=100), save :: anz_parallelSim_char
	     CHARACTER(len=300), save :: makedirectory
	     CHARACTER(len=100), save :: force_element_name, marker_element_name
		
		! Start TFR 19.04.13
		
	     CHARACTER(len=100), save :: jointARname, jointIRname
	     INTEGER 				:: ID_JointAR, ID_JointIR
	     DOUBLE PRECISION 		:: CFJointAR(8), CFJointIR(8)
		! Ende TFR 19.04.13
		
		! Start TFR 22.04.13
	     DOUBLE PRECISION 		:: nKF_ideal, nWK_ideal
	     DOUBLE PRECISION 		:: nAR, nIR
		! Ende TFR 22.04.13
		
		
		
		
		
C ----------------------------------------------------------------------
C Berechnungsvariablen WK/LB Kontakt
C ----------------------------------------------------------------------	
	  ! ##### Marker IDs #####
      INTEGER				:: ID_Aussen, ID_Innen, ID_Waelz, ID_Waelz_A, ID_Ground 
      INTEGER				:: ID_lb_Aussen
	  	
      INTEGER, PARAMETER	:: no_slce_LB = 41
     	  
	  ! Geometrien des Außen- und Innenrings
      DOUBLE PRECISION		:: rad_AR, prorad_AR, AR_breite, AR_dicke
      DOUBLE PRECISION		:: rad_IR, prorad_IR, IR_breite
	  
	  ! Geometrien des Wälzkörpers
      INTEGER				:: mid_slce
      DOUBLE PRECISION		:: rad_WK, l_WK
      INTEGER				:: protype
      DOUBLE PRECISION		:: prorad_WK
      DOUBLE PRECISION		:: WKpro_ap, WKpro_cp, WKpro_dp, WKpro_kp, WKpro_rk	
	  
	  ! Werkstoffdaten des Außen- und Innenrings und des Wälzkörpers
      DOUBLE PRECISION		:: IR_E, IR_v, AR_E, AR_v, WK_E, WK_v
	  
	  ! ##### Variablen Reibung/Schmierung #####
      DOUBLE PRECISION		:: vtang_rel(no_slce_LB,3), vtang_sum(no_slce_LB,3)
      INTEGER				:: race_f_mod, race_f_EHD, race_lub_mod, tau_mod
      DOUBLE PRECISION		:: vel_s, vel_d, mu_s, mu_d
      DOUBLE PRECISION		:: etaZero, alphaT, alphaP, lambdaZero, alphaLambda, lubtmp 
      INTEGER				:: filmT_mod, comprT_mod	
      DOUBLE PRECISION		:: C1, C2
      DOUBLE PRECISION		:: AV, BV, CV, DR, ER, pR
      DOUBLE PRECISION		:: B, C, K, a1, a2, b1, b2
      DOUBLE PRECISION		:: rhoZero, tauRheo
      DOUBLE PRECISION		:: sigma, BZH, CZH
      DOUBLE PRECISION		:: alphaHys, scaleHys, cr, rexp
      DOUBLE PRECISION 		:: h0(no_slce_LB), h0th(no_slce_LB) 
      DOUBLE PRECISION		:: h0th_sl(no_slce_LB) 
	  
	  ! ##### Variablen Dämpfung #####
      INTEGER				:: d_mod
      DOUBLE PRECISION		:: d_max, p_t
      DOUBLE PRECISION		:: K0, KR, KL, KE, Keta, Kalpha, Kq, Ku, Kf, fe
	  
	  ! ##### Kontaktvariablen #####
      
      DOUBLE PRECISION		:: penetrtn(no_slce_LB), prec
      DOUBLE PRECISION		:: ctnorm(no_slce_LB,3), ctpoint(no_slce_LB,3)
	  	  	   
	  ! ##### Vektor mit Parametern für Scheibenmodell, Gauss-Integration #####
      INTEGER				:: no_gauss_LB, cf_pnts_LB, ctctype_LB
      DOUBLE PRECISION		:: cf_p_max_LB
	  	  
	  ! ##### Vektor mit GForce-IDs für Ausgabe von Ergebnissen für PostProcessor/Datei #####
      INTEGER				:: gfo_id_AR, gfo_id_IR	  
		  
	  ! ##### Sonstige Variablen #####	 
      DOUBLE PRECISION		:: slce_wdth     
      DOUBLE PRECISION		:: distnce(no_slce_LB)
      DOUBLE PRECISION		:: pro_WK(no_slce_LB), dWKpro_dEta(no_slce_LB)     
      DOUBLE PRECISION		:: k1_AR, k2_AR
      DOUBLE PRECISION		:: beta_schr, gamma_kipp
      DOUBLE PRECISION		:: k1_IR, k2_IR
      DOUBLE PRECISION		:: temp1, temp1a
      DOUBLE PRECISION		:: temp33(3,3)	  
      DOUBLE PRECISION 		:: mat_WK, mat_AR, mat_c_WKAR, E_dash_WKAR
      DOUBLE PRECISION 		:: rho1x_WKAR(no_slce_LB), rho2x_WKAR, rho1y_WKAR, rho2y_WKAR
      DOUBLE PRECISION 		:: rhox_WKAR(no_slce_LB), rhoy_WKAR, sum_rho_WKAR(no_slce_LB)  
      DOUBLE PRECISION 		:: R_dash_WKAR(no_slce_LB)
      DOUBLE PRECISION 		:: mat_IR, mat_c_WKIR, E_dash_WKIR
      DOUBLE PRECISION 		:: rho1x_WKIR(no_slce_LB), rho2x_WKIR, rho1y_WKIR, rho2y_WKIR
      DOUBLE PRECISION 		:: rhox_WKIR(no_slce_LB), rhoy_WKIR, sum_rho_WKIR(no_slce_LB)
      DOUBLE PRECISION 		:: R_dash_WKIR(no_slce_LB)	  
      DOUBLE PRECISION 		:: p_slce(no_slce_LB), b_slce(no_slce_LB)
     
		  		  		  
	  ! ##### Auslesefelder aus Simpack #####
      DOUBLE PRECISION		:: temptdisp, temprdisp
      DOUBLE PRECISION		:: tdisp_wk_ar_gr(3)
      DOUBLE PRECISION		:: tdisp_wk_wka_gr(3), tdisp_wk_wka_wk(3)
      DOUBLE PRECISION		:: angles_ar_gr(3), rdisp_wk_ar(3), rdisp_ar(3)
      DOUBLE PRECISION		:: tdisp_wk_ir_gr(3), angles_ir_gr(3)
      DOUBLE PRECISION		:: rdisp_wk_ir(3), rdisp_ir(3)
      DOUBLE PRECISION		:: rvel_ar_gr_gr(3), tvel_ar_gr_gr(3) 
      DOUBLE PRECISION		:: rvel_ir_gr_gr(3), tvel_ir_gr_gr(3) 
      DOUBLE PRECISION		:: rvel_wk_gr_gr(3), tvel_wk_gr_gr(3)
      DOUBLE PRECISION		:: rvel_wk_ar_gr(3), tvel_wk_ar_gr(3)
      DOUBLE PRECISION		:: rvel_wk_ir_gr(3), tvel_wk_ir_gr(3)
      DOUBLE PRECISION		:: tdisp_ar_ara_gr(3), tdisp_ar_ara_ar(3)
	  
		! ##### Flexibler Aussenring #####
	INTEGER::            FlexARmod, id_Flex
	DOUBLE PRECISION::   NForce_IR_GR(3), NForce_IR_GR_mag
	DOUBLE PRECISION::   FE_NForceFlex, psiFlex, gammaFlex
	DOUBLE PRECISION::   delta_rad_AR, rad_AR_flex
	  	  
	  ! ##### Variablen Summenkräfte #####	  
      DOUBLE PRECISION 		:: NForce(no_slce_LB,3), NMoment(no_slce_LB,3)
      DOUBLE PRECISION 		:: DForce(no_slce_LB,3), DMoment(no_slce_LB,3)
      DOUBLE PRECISION 		:: TForce(no_slce_LB,3), TMoment(no_slce_LB,3)
      DOUBLE PRECISION		:: sumNForce(3), sumNMoment(3)
      DOUBLE PRECISION		:: sumDForce(3), sumDMoment(3)
      DOUBLE PRECISION		:: sumTForce(3), sumTMoment(3)
      DOUBLE PRECISION		:: result(6)
	  	  
      INTEGER				:: $M_WK1	 
	  
	  
	  
C ----------------------------------------------------------------------
C  Berechnungsvariablen Bordkräfte
C ----------------------------------------------------------------------	
	
	 ! ##### Marker IDs #####
      INTEGER				:: ID_Kaefig 

	  
	  ! ##### Kontaktvariablen #####
      INTEGER				:: num_contacts
	  
	  ! Geometrien des Außen- und Innenrings
      DOUBLE PRECISION		:: AR_LFB_breite, Bord_dicke
      DOUBLE PRECISION		:: AR_Bord_D, AR_Bord_AD, AR_Bord_ID
     	  
      DOUBLE PRECISION		:: IR_LFB_breite, Bord_dicke_IR
      DOUBLE PRECISION		:: IR_Bord_D, IR_Bord_AD, IR_Bord_ID
      DOUBLE PRECISION		:: bord_w_ar, bord_w_ir,bord_oeffnungswinkel_AR,bord_oeffnungswinkel_IR
      DOUBLE PRECISION		:: rad_teilkreis
      
	 
	  ! ##### Vektor mit Parametern für Scheibenmodell, Gauss-Integration #####
      INTEGER, PARAMETER	:: no_slce_Bord = 2
	  
	  ! ##### Variablen Reibung #####
      DOUBLE PRECISION		:: vtang_rel_brd(no_slce_Bord,3), vtang_sum_brd(no_slce_Bord,3)
      INTEGER				:: race_f_mod_brd
      DOUBLE PRECISION		:: vel_s_brd, vel_d_brd, mu_s_brd, mu_d_brd
	  
	  ! ##### Variablen Dämpfung #####     
      DOUBLE PRECISION		:: d_max_brd, p_t_brd
	  
	  ! ##### Sonstige Variablen #####	 
      DOUBLE PRECISION		:: y, h, r, chord_l_a,d,chord_l_i
      DOUBLE PRECISION		:: temp1_brd, temp1a_brd
      DOUBLE PRECISION		:: temp33_brd(3,3)
	  
	  ! ##### Auslesefelder aus Simpack #####
      DOUBLE PRECISION		:: temptdisp_brd
      DOUBLE PRECISION		:: tdisp_wk_wka_wk_brd(3), tdisp_ar_ara_ar_brd(3)
	  DOUBLE PRECISION		:: tdisp_wk_ar_kf_brd(3), tdisp_wk_ir_kf_brd(3)
      DOUBLE PRECISION		:: angles_ar_gr_brd(3), angles_wk_ar_brd(3),angles_wk_ir_brd(3)
      DOUBLE PRECISION		:: tdisp_ir_ira_ir_brd(3), tdisp_wk_Bord_gr_brd(3)     
      DOUBLE PRECISION		:: tdisp_wk_ar_gr_brd(3),tdisp_wk_wka_gr_brd(3)
      DOUBLE PRECISION		:: tdisp_ar_ara_gr_brd(3), tdisp_ar_gr_gr_brd(3) 	
      DOUBLE PRECISION		:: tdisp_wk_ir_gr_brd(3), tdisp_ir_ira_gr_brd(3)
      DOUBLE PRECISION		:: tdisp_ir_gr_gr_brd(3), tdisp_wk_gr_gr_brd(3)    
      DOUBLE PRECISION		:: angles_ir_gr_brd(3)
      DOUBLE PRECISION		:: tdisp_wk_kf_wk_brd(3)
      DOUBLE PRECISION		:: gamma_kipp_brd
	  
      DOUBLE PRECISION		:: rvel_wk_ar_gr_brd(3),tvel_wk_ar_gr_brd(3)
      DOUBLE PRECISION		:: rvel_Bord_gr_gr_brd(3), rvel_wk_gr_gr_brd(3)
      DOUBLE PRECISION		:: tvel_wk_Bord_gr_brd(3), tvel_wk_gr_gr_brd(3)
      DOUBLE PRECISION		:: tvel_Bord_gr_gr_brd(3),rvel_wk_Bord_gr_brd(3)
      DOUBLE PRECISION		:: rvel_wk_ir_gr_brd(3), tvel_wk_ir_gr_brd(3)
	  
	  ! ##### Variablen Summenkräfte #####	  
      DOUBLE PRECISION 		:: NForce_brd(2,3), NMoment_brd(2,3)
      DOUBLE PRECISION 		:: DForce_brd(2,3), DMoment_brd(2,3)
      DOUBLE PRECISION 		:: TForce_brd(2,3), TMoment_brd(2,3)
      DOUBLE PRECISION		:: sumNForce_brd(3), sumNMoment_brd(3)
      DOUBLE PRECISION		:: sumDForce_brd(3), sumDMoment_brd(3)
      DOUBLE PRECISION		:: sumTForce_brd(3), sumTMoment_brd(3)
      DOUBLE PRECISION		:: result_brd(6)
	  
	  
	  
	  
C ----------------------------------------------------------------------
C  Berechnungsvariablen Taschenfederkäfig
C ----------------------------------------------------------------------
 
	  ! ##### Marker IDs #####
      INTEGER				:: ID_Tasche


	  ! ##### Auslesefelder aus Simpack #####
      DOUBLE PRECISION		:: temptdisp_tfk, temp1_tfk, temp1a_tfk
      DOUBLE PRECISION		:: tdisp_wk_kfT_kfT(3), tdisp_wk_kf_kf(3)
      DOUBLE PRECISION		:: angles_wk_kfT(3)
      DOUBLE PRECISION		:: rvel_wk_kfT_kfT(3), tvel_wk_kfT_kfT(3)
      DOUBLE PRECISION		:: vel_step1,vel_step2, vel_step3
      DOUBLE PRECISION		:: vel_step4,vel_step5, vel_step6
	  
     
      ! ##### Parameter #####	 
      DOUBLE PRECISION		:: taschenspiel_tang, taschenspiel_rad, taschenspiel_ax
      DOUBLE PRECISION		:: wk_len, wk_profrad
		
		   ! ##### Sonstige Variablen #####
      DOUBLE PRECISION		:: temp33_tfk(3,3)
	 
	  ! ##### Variablen Summenkräfte #####	 
      DOUBLE PRECISION		:: result_tfk(6)	 
	  
	  
	  
	  
C ----------------------------------------------------------------------
C Berechnungsvariablen Kontakkäfig
C ----------------------------------------------------------------------
	  ! ##### Marker IDs #####
      INTEGER				:: ID_KF_Aussen
                
      INTEGER, PARAMETER	:: no_slce_KF = 21
	
	  ! Geometrien des Käfigs
      DOUBLE PRECISION		:: KF_Ta_rad, prorad_KF, KF_Stg_Dicke
	

	  
	  ! Werkstoffdaten des Käfigs und des Wälzkörpers
      DOUBLE PRECISION		:: KF_v, KF_E
	  	  
	  ! ##### Variablen Reibung/Schmierung #####
      DOUBLE PRECISION		:: vtang_rel_KF(no_slce_KF,3), vtang_sum_KF(no_slce_KF,3)
      DOUBLE PRECISION		:: vtang_rel2_KF(no_slce_KF,3), vtang_sum2_KF(no_slce_KF,3)
      INTEGER				:: race_f_mod_KF
      DOUBLE PRECISION		:: vel_s_KF, vel_d_KF, mu_s_KF, mu_d_KF
	  
	  ! ##### Variablen Dämpfung #####
      INTEGER				:: d_mod_KF	
      DOUBLE PRECISION		:: d_max_KF, p_t_KF
	  
	  ! ##### Kontaktvariablen #####
      DOUBLE PRECISION		:: penetrtn_KF(no_slce_KF), penetrtn2_KF(no_slce_KF)
      DOUBLE PRECISION		:: ctnorm_KF(no_slce_KF,3), ctnorm2_KF(no_slce_KF,3)
      DOUBLE PRECISION		:: ctpoint2_KF(no_slce_KF,3), ctpoint2(no_slce_KF,3)
	  
	 ! ##### Vektor mit Parametern für Scheibenmodell, Gauss-Integration, Curve Fit #####
      INTEGER				:: ctctype_KF, cf_pnts_KF
      DOUBLE PRECISION		:: cf_p_max_KF
	  
	  ! ##### Sonstige Variablen #####	 
      DOUBLE PRECISION		:: slce_wdth_KF, mid_slce_KF
      DOUBLE PRECISION		:: distnce_KF(no_slce_KF)	
      DOUBLE PRECISION		:: pro_WK_KF(no_slce_KF), dWKpro_dEta_KF(no_slce_KF)  	
      DOUBLE PRECISION 		:: mat_KF, mat_c_WKKF 
      DOUBLE PRECISION 		:: E_dash_WKKF      
      DOUBLE PRECISION 		:: rho1x_WKKF(1:no_slce_KF), rho2x_WKKF
      DOUBLE PRECISION 		:: rho1y_WKKF, rho2y_WKKF
      DOUBLE PRECISION 		:: rhox_WKKF(1:no_slce_KF), rhoy_WKKF
      DOUBLE PRECISION      :: sum_rho_WKKF(1:no_slce_KF)
      DOUBLE PRECISION      :: R_dash_WKKF(1:no_slce_KF)
      DOUBLE PRECISION		:: beta_schr_KF, gamma_kipp_KF
      DOUBLE PRECISION		:: k1_KF, k2_KF
      DOUBLE PRECISION		:: temp33_KF(3,3)
      DOUBLE PRECISION		:: temp1_KF, temp1a_KF
	  INTEGER				:: contactlocl
	  
	  ! ##### Auslesefelder aus Simpack #####
      DOUBLE PRECISION		:: temptdisp_KF
      DOUBLE PRECISION		:: tdisp375_KF(3),tdisp345_KF(3),tdisp365_KF(3),tdisp685_KF(3)
      DOUBLE PRECISION		:: dist_au_WK(3), dist_au_KF(3), tdisp377(3)
      DOUBLE PRECISION		:: rvel655_KF(3),tvel365_KF(3)
      DOUBLE PRECISION		:: rvel355_KF(3),rvel365_KF(3)
	 	  
	  ! ##### Variablen Summenkräfte #####	
      DOUBLE PRECISION 		:: NForce_KF(no_slce_KF,3), NMoment_KF(no_slce_KF,3)
      DOUBLE PRECISION 		:: NForce2_KF(no_slce_KF,3), NMoment2_KF(no_slce_KF,3)
      DOUBLE PRECISION 		:: DForce_KF(no_slce_KF,3), DMoment_KF(no_slce_KF,3)
      DOUBLE PRECISION 		:: DForce2_KF(no_slce_KF,3), DMoment2_KF(no_slce_KF,3)
      DOUBLE PRECISION 		:: TForce_KF(no_slce_KF,3), TMoment_KF(no_slce_KF,3)
      DOUBLE PRECISION 		:: TForce2_KF(no_slce_KF,3), TMoment2_KF(no_slce_KF,3)
      DOUBLE PRECISION 		:: TForce_out1_KF(no_slce_KF,3)
      DOUBLE PRECISION 		:: vtang_rel1_mag_KF(no_slce_KF)
      DOUBLE PRECISION 		:: TForce_out2_KF(no_slce_KF,3)
      DOUBLE PRECISION 		:: vtang_rel2_mag_KF(no_slce_KF)	 
      DOUBLE PRECISION		:: sumNForce_KF(3), sumNMoment_KF(3)
      DOUBLE PRECISION		:: sumDForce_KF(3), sumDMoment_KF(3)
      DOUBLE PRECISION		:: sumTForce_KF(3), sumTMoment_KF(3)
      
      DOUBLE PRECISION		:: result_KF(6)
	  
	  
	  
	  
	  
C ----------------------------------------------------------------------
C Berechnungsvariablen Führungsbord
C ----------------------------------------------------------------------
	  ! ##### Marker IDs #####
      INTEGER				:: ID_KF_Bord, ID_Bord, gfo_id
      
	! ##### Geometrievektor (Käfig) #####        
      DOUBLE PRECISION		:: kf_innenrad, kf_aussenrad 
      DOUBLE PRECISION		:: KF_steg_br,IR_Bord_rad, AR_Bord_rad
	  	  
	  ! ##### Materialdaten #####
      DOUBLE PRECISION		::  ar_poisson,ar_youngs,ir_poisson, ir_youngs
      DOUBLE PRECISION		::  kf_poisson,kf_youngs
	  
	  ! ##### Variablen Reibung/Schmierung #####
      DOUBLE PRECISION		::  etazero_FB
	  
	  ! ##### Variablen Dämpfung #####
      DOUBLE PRECISION		:: kf_d_max, kf_p_t
	  DOUBLE PRECISION		:: a,delta
	  ! ##### Variablen Käfig ##### 
      INTEGER				:: kaefigmod 			
      DOUBLE PRECISION		:: kf_prec       

	    ! ##### Sonstige Variablen #####
      DOUBLE PRECISION		:: exzent
      DOUBLE PRECISION		:: temp1_FB, temp_FB
      DOUBLE PRECISION		:: temp33_FB(3,3)
	  
	  ! ##### Auslesefelder aus Simpack #####
      DOUBLE PRECISION		:: temptdisp_FB
      DOUBLE PRECISION		:: tdisp788_FB(3),tdisp785_FB(3),tdisp755_FB(3) 
      DOUBLE PRECISION		:: transf_LB_ref_FB(3,3)
      DOUBLE PRECISION		:: rvel785_FB(3),tvel785_FB(3)
	 
	 ! ##### Variablen Summenkräfte #####		
      DOUBLE PRECISION		:: kf_NForce(3), kf_DForce(3)
      DOUBLE PRECISION		:: kf_TForce(3), kf_TMoment(3)
      DOUBLE PRECISION		:: result_FB(6)
	

		
		!-----------------------------Definiton contactloc ---------------------------
		
		INTEGER				:: contactloc
		contactloc	= int(par(6))
 	 	!ehem. uforce22 WK/LB-Kontakt	
											! 1  - WK-AR
											! 2  - WK-IR
		
		
 	 	!ehem. uforce23 Bordkräfte	
											! 3  - AR
											! 4  - IR
											
		!ehem. uforce24 Taschenfederkäfig   ! 5
		!ehem. uforce25 Kontaktkäfig		! 6
		
		!ehem. uforce26 MA/MB-Käfig 		! 7
											


		
										
		
		
	   if (contactloc == 1 .or. contactloc == 2)then	
!---------------------------------------------------------------------------------------------		
!---------------------------------------------------------------------------------------------
!-------------------------------------- WÄLZKÖRPER LAUFBAHN KONTAKT---------------------------
!---------------------------------------------------------------------------------------------		
!---------------------------------------------------------------------------------------------		
	
		
 

C ----------------------------------------------------------------------
C Initialisation
C ----------------------------------------------------------------------
		ierr = 0
	  
	    mid_slce		= (no_slce_LB/2)+1 ! Index der mittleren Scheibe
		
	    ! ##### Marker #####
		ID_Aussen   = int(par(1))    	! Marker ID Aussenring
		ID_Innen    = int(par(2))	 	! Marker ID Innenring
		ID_Waelz    = int(par(3))	 	! Marker ID Waelzkoerper
		ID_Waelz_A  = int(par(4))    	! Marker ID Waelzkoerper-aussen
		ID_Ground   = int(par(5))    	! Marker ID Ground

				
				
		! ##### Geometrievektor 1 (Außen- und Innenring) #####
		rad_AR 		= (par(7))/2.0   	! Radius Laufbahn Außenring
		prorad_AR 	= (par(8)) 		 	! Profilradius Laufbahn Außenring
		rad_IR 		= (par(9))/2.0 		! Radius Laufbahn Innenring
		prorad_IR	= (par(10)) 		! Profilradius Laufbahn Innenring
		AR_dicke	= (par(85))			! Ringdicke Außenring
			
		! ##### Geometrievektor 2 (Wälzkörper) #####
		l_WK 		= (par(11)) 	 	! Länge des Wälzkörpers
		rad_WK 		= (par(12))/2.0     ! Radius des Wälzkörpers
		prorad_WK 	= (par(13))         ! Profilradius
		protype		= int(par(14))	 	!Profil Typ:	
											! 1 - zylindrisches profil
											! 2 - kreisprofil
											! 3 - din iso 281 mit geradenstück und rundungsradius
											! 4 - kreisprofil mit rundungsradius
											! 5 - zylindrisches profil mit kantenradius
		WKpro_ap 	= (par(15)) 	    ! WK-profil nach DIN-ISO
		WKpro_cp 	= (par(16))    	    ! WK-profil nach DIN-ISO: effektive WK-Länge
		WKpro_dp 	= (par(17))     	! WK-profil nach DIN-ISO: Länge Zylinderstück
		WKpro_kp 	= (par(18))         ! WK-profil nach DIN-ISO: Beginn Geradenstück
		WKpro_rk 	= (par(19)) 	 	! WK-profil nach DIN-ISO: Kantenradius
		
		! Vektor mit Materialdaten
		IR_E		= (par(20)) 		! Elastizitätsmodul des IR
		IR_v		= (par(21))         ! Querkontraktionszahl des IR
		AR_E		= (par(22)) 		! Elastizitätsmodul des AR
		AR_v		= (par(23))         ! Querkontraktionszahl des AR
		WK_E		= (par(24)) 		! Elastizitätsmodul des Wälzkörpers
		WK_v		= (par(25))         ! Querkontraktionszahl des Wälzkörpers
				
		! ##### Vektor mit Reibwert- und Schmierstoffdaten #####
		race_f_mod 	= int(par(26))     	! Festkörperreibmodell:	
											! 1 - linear  
											! 2 - kubisch
											! 3 - arctan
		race_f_EHD 	= int(par(27))     	! EHD-Reibung:  
											! 0 - aus
											! 1 - an (mit Festkörperreibung aus race_f_mod und Hysteresereibung
		race_lub_mod = int(par(28))   	! Schmierstoffbeschreibung:	
											! 1 - FVA 400
											! 2 - Teutsch
		tau_mod 	= int(par(29)) 		! Modell zur Berechnung der Schubspannung im Schmierstoff
											! 1 - Ree-Eyring-Modell (maximale Schubspannung unbegrenzt)
											! 2 - Bair-Winer-Modell (Schubspannung begrenzt)
											! 3 - Modell nach Fassbender (ohne Gleitanteil)
		filmT_mod 	= int(par(30)) 		! Modell für thermischen Korrekturfaktor
											! 1 - Modell nach Murch und Wilson (bei geringem Gleitanteil)
											! 2 - Modell nach Zhu und Cheng (mit/ohne Gleitanteil)
		comprT_mod  = int(par(31))    	! Modell zur Berücksichtigung der Schmierstofferwärmung aufgrund von Kompression
											! 1 - Modell nach Gold
											! 2 - Modell nach Dicke				
		vel_s   	= (par(32)) 		! Schlupfgeschwindigkeit bei mu_s
		vel_d		= (par(33)) 		! Schlupfgeschwindigkeit bei mu_d
		mu_s		= (par(34)) 		! mu statisch
		mu_d		= (par(35)) 		! mu dynamisch
		lubtmp		= (par(36)) 		! Temperatur des verwendeten Öles [°C]
		etaZero     = (par(37)) 		! Dynamische Viskosität bei lubTmp [MPas]
		alphaT		= (par(38)) 		! Temperaturviskositätskoeffizient [1/K]
		alphaP		= (par(39)) 		! Druckviskositätskoeffizient [1/MPa]
		lambdaZero 	= (par(40)) 		! Wärmeleitfähigkeit [W/mK]
		alphaLambda = (par(41)) 		! Wärmeleitfähigkeitskoeffizient [W/mK^2]		
		C1          = (par(42)) 		! Konstante zur Bestimmung der Temperatur im Kontaktbereich [bar/K^2]
		C2			= (par(43)) 		! Konstante zur Bestimmung der Temperatur im Kontaktbereich [bar/K^2]
		AV			= (par(44)) 		! Rodermund T-p-Viskositätsparameter [mPas]
		Bv			= (par(45)) 		! Rodermund T-p-Viskositätsparameter [°C]
		CV		 	= (par(46)) 		! Rodermund T-p-Viskositätsparameter [°C]
		DR			= (par(47)) 		! Rodermund T-p-Viskositätsparameter [-]
		ER 			= (par(48)) 		! Rodermund T-p-Viskositätsparameter [-]
		pR			= (par(49)) 		! Rodermund T-p-Viskositätsparameter [bar]
		B			= (par(50))			! [°C] Parameter B, C, K zur Berechnung der Viskosität bei _lubT und Umgebungsdruck
		C			= (par(51))			! [°C]
		K			= (par(52))			! [mPas]
		a1			= (par(53))			! [bar] Parameter a1, a2, b1, b2 zur Berechnung der Viskosität bei _lubT und Kontaktdruck und des Druck-Viskositätskoeffizienten
		a2			= (par(54))			! [bar/°C]
		b1			= (par(55))			! [-]
		b2			= (par(56))			! [1/°C]
		rhoZero		= (par(57)) 		! [g/ml] Dichte bei 15°C
		tauRheo		= (par(58)) 		! [MPa] Spannung im Ree-Eyring- bzw. Bair-Winer-Modell
		sigma		= (par(59))			! Kombinierte Standardabweichung der Rauheiten beider Kontaktkörper [mm]
		BZH			= (par(60))			! Konstante zur Mischreibungsberechnung nach Zhou und Hoeprich (Lasttraganteil) [-]
		CZH			= (par(61))			! Konstante zur Mischreibungsberechnung nach Zhou und Hoeprich (Lasttraganteil) [-]
		alphaHys	= (par(62)) 		! Hysterese-Verlustfaktor (bei trockener Wälzreibung) - wird nur in Kombination mit EHD berücksichtigt
		scaleHys	= (par(63)) 		! Skalierungsfaktor für Skalierungsfunktion bei Hysteresereibung
		cr			= (par(64)) 		! Rollwiderstandskoeffizient (Reynolds-Verlustmonent)
		rexp		= (par(65)) 		! Rollwiderstandsexponent (Reynolds-Verlustmonent)			
		
		! ##### Vektor mit Dämpfungsdaten ##### 
		d_mod 		= int(par(66))    	! Dämpfungsmodell (momentan nur ein möglich)
		p_t			= (par(67)) 		! Durchdringungstiefe max. für Materialdämpfung
		d_max		= (par(68)) 		! Maximale Dämpfung
		K0			= (par(69))			! Dämpfungsparameter nach Dietl [(kg**-0,04)*(m**0,06)*(s**0,0115)]
		KR			= (par(70))			! Dämpfungsparameter nach Dietl [-]
		KL			= (par(71))			! Dämpfungsparameter nach Dietl [-]
		KE			= (par(72))			! Dämpfungsparameter nach Dietl [-]
		Keta		= (par(73))			! Dämpfungsparameter nach Dietl [-]
		Kalpha		= (par(74))			! Dämpfungsparameter nach Dietl [-]
		Kq			= (par(75))			! Dämpfungsparameter nach Dietl [-]
		Ku			= (par(76))			! Dämpfungsparameter nach Dietl [-]
		Kf			= (par(77))			! Dämpfungsparameter nach Dietl [-]
		fe			= (par(78))			! Dämpfungsparameter nach Dietl [-]
				
		! ###### Vektor mit Parametern für Scheibenmodell, Gauss-Integration, Curvefit #####
		ctctype_LB 	= int(par(79))   	! Contact Type:
											! 1 - Scheibenmodell AST/Tripp (höchste Genauigkeit)
											! 2 - Scheibenmodell Tripp approximiert
											! 3 - Scheibenmodell DIN ISO 281
											! 4 - Hertz !!! NOCH NICHT IMPLEMENTIERT !!!	  
		no_gauss_LB = int(par(80)) 		! Anzahl der Gauss-Integrationspunkte
		cf_pnts_LB	= int(par(81)) !100 				! Anzahl der Curvefit-Punkte
	    cf_p_max_LB	= (par(82))    !4000.0 			! Maximale Flächenpressung für Curvefit
		
		! ##### Vektor mit GForce-IDs für Ausgabe von Ergebnissen in PostProcessor/Datei #####
		gfo_id_AR	= int(par(83))  	! GForce-ID für Wälzkörper-Außenring-Kontakt
		gfo_id_IR	= int(par(84)) 		! GForce-ID für Wälzkörper-Innenring-Kontakt

        FlexARmod       = int(par(86))
		FE_NForceFlex   = par(87)
		
		ausgabe	= int(par(88))


		pi = 4.0d0*atan(1.0d0)
		
C ----------------------------------------------------------------------
C Execution
C ----------------------------------------------------------------------
	  
	 !--------- Nullinitialisierung der Variablen ----------------
	  prec			= 0.00000001
         
	  rdisp_wk_ar(:)= 0.0
	  rdisp_ar(:) 	= 0.0
	  rdisp_wk_ir(:)= 0.0
	  rdisp_ir(:)	= 0.0
	  
	  ! ##### Kontaktkraft Parameter #####
	  !k1_AR			= 0.0
	  !k2_AR			= 0.0
	  !k1_IR			= 0.0
	  !k2_IR			= 0.0
	  	  
	  ! ##### Pressung Parameter #####	 
	  p_slce		= 0.0
	  b_slce		= 0.0
	  
	  ! ##### Reduzierter Elastizitätsmodul parameter #####	  
	  !E_dash_WKAR   = 0.0
	  !R_dash_WKAR   = 0.0
	  	     	  
	  ! ##### Sonstige Parameter #####	  
	  NForce 		= 0.0d0
	  NMoment 		= 0.0d0
	  DForce 		= 0.0d0
	  DMoment 		= 0.0d0
	  TForce 		= 0.0d0
	  TMoment 		= 0.0d0
	  !hehd 		= 0.0
	  	  	  
!---------------------------------------------------------------------------------------------
!-------------------------------------- Wälzkörperprofil -------------------------------------
!---------------------------------------------------------------------------------------------

! Breite einer Scheibe
	    slce_wdth = l_WK/no_slce_LB

! Abstand der Scheibe i vom Wälzkörpermittelpunkt
        do i=1,no_slce_LB
			distnce(i) = slce_wdth*(i-0.5)-l_WK/2
        enddo

! Wälzkörperprofil: pro_WK(i) und dWKpro_dEta(i)
        call profiledetect(iflag, protype, no_slce_LB, rad_WK,                                  
     &				l_WK, prorad_WK, distnce(1:no_slce_LB), pro_WK(1:no_slce_LB),	
     &					WKpro_ap, WKpro_cp, WKpro_dp, WKpro_kp, WKpro_rk,           
     &					dWKpro_dEta(1:no_slce_LB))
	 
	 
!---------------------------------------------------------------------------------------------
!------------------- Reduzierter Elastizitätsmodul und reduzierter Radius --------------------
!---------------------------------------------------------------------------------------------	 
        ! WK-AR-Kontakt
        if(contactloc == 1) then

			! Reduzierter Elastizitätsmodul 
			mat_WK      = (1.0d0-WK_v**2.0d0)/WK_E
			mat_AR      = (1.0d0-AR_v**2.0d0)/AR_E
			mat_c_WKAR  = (mat_WK+mat_AR)/2.0d0
			E_dash_WKAR = 1.0d0/mat_c_WKAR

			! Reduzierter Radius
			rho1x_WKAR(1:no_slce_LB) = 1.0d0/pro_WK(1:no_slce_LB)
			rho2x_WKAR               = -1.0d0/rad_AR
	    
			! Für Hertz-Kontaktmodell in Kombination mit Wälzkörperkreisprofil
            if(ctctype_LB==4 .and. protype==2) then
				rho1y_WKAR = 1.0d0/prorad_WK
				rho2y_WKAR = 1.0d0/prorad_AR
			! Näherung: Linienkontakt mit nicht profilierten Kontaktpartnern
            else
				rho1y_WKAR = 0.0d0
				rho2y_WKAR = 0.0d0	        
            endif

			rhox_WKAR(1:no_slce_LB) = rho1x_WKAR(1:no_slce_LB)+rho2x_WKAR
			rhoy_WKAR               = rho1y_WKAR+rho2y_WKAR
	    
            if(abs(rad_AR)>0.0d0) then
				sum_rho_WKAR(1:no_slce_LB) = rhox_WKAR(1:no_slce_LB)+rhoy_WKAR 
            else
				sum_rho_WKAR(1:no_slce_LB) = rho1x_WKAR(1:no_slce_LB)           ! Wälzkörper-Ebene Kontakt
            endif	    
			R_dash_WKAR(1:no_slce_LB) = 1.0d0/sum_rho_WKAR(1:no_slce_LB)
        
        ! WK-IR-Kontakt
        elseif(contactloc == 2) then

			! Reduzierter Elastizitätsmodul 
			mat_WK      = (1.0d0-WK_v**2.0d0)/WK_E
			mat_IR      = (1.0d0-AR_v**2.0d0)/IR_E
			mat_c_WKIR  = (mat_WK+mat_IR)/2.0d0
			E_dash_WKIR = 1.0d0/mat_c_WKIR

			! Reduzierter Radius (Näherung: Linienkontakt mit nicht profilierten Kontaktpartnern)
			rho1x_WKIR(1:no_slce_LB) = 1.0d0/pro_WK(1:no_slce_LB)
			rho2x_WKIR               = 1.0d0/rad_IR

			! Für Hertz-Kontaktmodell in Kombination mit Wälzkörperkreisprofil
            if(ctctype_LB==4 .and. protype==2) then
				rho1y_WKIR  = 1.0d0/prorad_WK
				rho2y_WKIR  = 1.0d0/prorad_IR
            else
				rho1y_WKIR  = 0.0d0
				rho2y_WKIR  = 0.0d0	    	    
            endif

			rhox_WKIR(1:no_slce_LB) = rho1x_WKIR(1:no_slce_LB)+rho2x_WKIR
			rhoy_WKIR               = rho1y_WKIR+rho2y_WKIR
	    
            if(abs(rad_IR)>0.0d0) then
				sum_rho_WKIR(1:no_slce_LB) = rhox_WKIR(1:no_slce_LB)+rhoy_WKIR
            else
				sum_rho_WKIR(1:no_slce_LB) = rho1x_WKIR(1:no_slce_LB)           ! Wälzkörper-Ebene Kontakt
            endif	    
			R_dash_WKIR(1:no_slce_LB) = 1.0d0/sum_rho_WKIR(1:no_slce_LB)
    
        endif
	 	 	 
!---------------------------------------------------------------------------------------------
!-------------------------------------- Kontakterkennung --------------------------------------
!---------------------------------------------------------------------------------------------
       delta_rad_AR =0.0d0
! Durchdringung, Kontaktnormale und Kontaktpunkt
! Wälzkörper - Außenring
        if (contactloc == 1) then
	  
            call SPCK_AV_DXYZ( temptdisp, tdisp_wk_ar_gr, ID_Waelz, ID_Aussen, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp, tdisp_wk_wka_gr, ID_Waelz, ID_Waelz_A, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp, tdisp_wk_wka_wk, ID_Waelz, ID_Waelz_A, ID_Waelz, err)
	      			
			! Umrechnung von m in mm
			tdisp_wk_ar_gr(:)=tdisp_wk_ar_gr(:)*1.0d3
			tdisp_wk_wka_gr(:)=tdisp_wk_wka_gr(:)*1.0d3
			tdisp_wk_wka_wk(:)=tdisp_wk_wka_wk(:)*1.0d3
			
			call SPCK_AV_ANGLE(angles_ar_gr, ID_Aussen, ID_Ground, 3, err)
			
	      ! ##### Korrektur AR-Radius (flexibles Gehäuse) #####
	      if (FlexARmod == 0) then
					    rad_AR_flex = rad_AR
			
			elseif (FlexARmod == 1) then
			   		    call SPCK_AN_IFCTNNAME2ID(id_Flex, '$I_ARflex', err) 
    					
					    if (err > 0) then
						    id_Flex = 0
						    rad_AR_flex = rad_AR
					    endif
    					
					    ! Korrektur Radius AR-Laufbahn (bei starrer Ankopplung des Aussenrings keine Korrektur)
					    if(id_Flex > 0) then
    						
						    ! Kraft zwischen IR und ground
						    call SPCK_AV_FXYZ( NForce_IR_GR_mag, NForce_IR_GR, ID_Innen, ID_Ground, 0, err ) 
    						
						    ! Bestimmen des verformten AR-Laufbahnradius
    						
						    ! Winkelposition des Wälzkörpers
						    psiFlex = atan2(tdisp_wk_ar_gr(1),tdisp_wk_ar_gr(2))
    						
						    ! Winkel der angreifenden Kraft
						    gammaFlex = atan2(NForce_IR_GR(1),-NForce_IR_GR(2))
    						
    						    ! Auslesen des Laufbahnradiuskorrekturwertes
    						    ! delta_ar_lfb_rad ist die relative Laufbahnradiusänderung
						    call SPCK_AS_IFARRAY( delta_rad_AR, psiFlex, gammaFlex, id_Flex, 
     &											    0, -1, -1, 0, err ) 
    	 
    	                      ! Skalierung mit wirkender Kraft
						    delta_rad_AR = delta_rad_AR/FE_NForceFlex*NForce_IR_GR_mag
!    						    write(str,*) 'delta', delta_rad_AR
!			                call SPCK_UF_MESSAGE(MSG_INFO, str)
    						    
    						    ! Verformter Außenringlaufbahnradius
						    rad_AR_flex = rad_AR + delta_rad_AR				    
					    endif
					    
				else
					    call spck_uf_print( 'Falsche Übergabevariable bei FlexARmod', err )
				endif  	  
            call WK_LB_Kontakt(id, iflag, time, contactloc, no_slce_LB, rdisp_wk_ar, rdisp_ar,
     &						angles_ar_gr, tdisp_wk_ar_gr, tdisp_wk_wka_gr, tdisp_wk_wka_wk, distnce(1:no_slce_LB), rad_AR_flex,       
     &							prorad_AR, pro_WK(1:no_slce_LB), dWKpro_dEta(1:no_slce_LB),
     &								penetrtn, ctnorm, ctpoint,
     &									beta_schr, gamma_kipp)
	 
	 
!Durchdringung, Kontaktnormale und Kontaktpunkt
! Wälzkörper - Innenring   	  
        elseif (contactloc == 2) then
	  
            call SPCK_AV_DXYZ( temptdisp, tdisp_wk_ir_gr, ID_Waelz, ID_Innen, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp, tdisp_wk_wka_gr, ID_Waelz, ID_Waelz_A, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp, tdisp_wk_wka_wk, ID_Waelz, ID_Waelz_A, ID_Waelz, err)

			! Umrechnung von m in mm
			tdisp_wk_ir_gr(:)=tdisp_wk_ir_gr(:)*1.0d3
			tdisp_wk_wka_gr(:)=tdisp_wk_wka_gr(:)*1.0d3
			tdisp_wk_wka_wk(:)=tdisp_wk_wka_wk(:)*1.0d3
	 
            call SPCK_AV_ANGLE( angles_ir_gr, ID_Innen, ID_Ground, 3, err)
	  	  
            call WK_LB_Kontakt(id, iflag, time, contactloc, no_slce_LB, rdisp_wk_ir, rdisp_ir,
     &						angles_ir_gr, tdisp_wk_ir_gr, tdisp_wk_wka_gr, tdisp_wk_wka_wk, distnce(1:no_slce_LB), rad_IR,       
     &							prorad_IR, pro_WK(1:no_slce_LB), dWKpro_dEta(1:no_slce_LB),
     &								penetrtn, ctnorm, ctpoint,
     &									beta_schr, gamma_kipp)
        endif
		  
!---------------------------------------------------------------------------------------------
!------------------------ Berechnung der Kontaktnormalkräfte ------------------------
!---------------------------------------------------------------------------------------------
! Curvefit-Parameter k1 und k2 (nur für Kontaktmodell 1 - AST und 2 - Tripp)
        
        if(ctctype_LB <= 2) then

	    ! Außenring
            if (contactloc==1) then
			    call CurveFit(id, time, contactloc, no_slce_LB, cf_pnts_LB, 
     &				cf_p_max_LB, rad_WK, rad_AR, AR_dicke, mat_WK, mat_AR, AR_v,                 
     &              mat_c_WKAR, sum_rho_WKAR(mid_slce), k1_AR, k2_AR)
    	
	    ! Innenring
            elseif (contactloc==2) then
			    call CurveFit(id, time, contactloc, no_slce_LB, cf_pnts_LB, 
     &				cf_p_max_LB, rad_WK, rad_IR, 0.0d0, mat_WK, mat_IR, 0.0d0,                       
     &              mat_c_WKIR, sum_rho_WKIR(mid_slce), k1_IR, k2_IR)
            endif

        else
			k1_AR=0.0d0
			k2_AR=0.0d0
			k1_IR=0.0d0
			k2_IR=0.0d0
        endif

								
! Ohne Dämpfung
! Außenring	  
        if (contactloc == 1) then
            call WK_LB_Kontaktkraft(id, time, iflag, ctctype_LB, no_slce_LB, l_WK,     			    
     &								penetrtn, ctnorm, ctpoint, k1_AR, k2_AR,					
     &									NForce, NMoment)
	      
! Innenring
        elseif(contactloc == 2) then
            call WK_LB_Kontaktkraft(id, time, iflag, ctctype_LB, no_slce_LB, l_WK, 			        
     &									penetrtn, ctnorm, ctpoint, k1_IR, k2_IR,					
     &										NForce, NMoment)
        endif
		     			
!---------------------------------------------------------------------------------------------
!------------------------ Berechnung der Relativgeschwindigkeiten im Kontakt -----------------
!---------------------------------------------------------------------------------------------	 		
      
! Außenring	 
        if(contactloc == 1) then
		
            call SPCK_AV_WXYZ(temp1, rvel_ar_gr_gr, ID_Aussen, ID_Ground, ID_Ground, err)
            call SPCK_AV_VXYZ(temp1, temp1a, tvel_ar_gr_gr, ID_Aussen, ID_Ground, ID_Ground, ID_Ground, err)
            call SPCK_AV_WXYZ(temp1, rvel_wk_gr_gr, ID_Waelz, ID_Ground, ID_Ground, err)
            call SPCK_AV_VXYZ(temp1, temp1a, tvel_wk_gr_gr, ID_Waelz, ID_Ground, ID_Ground, ID_Ground, err)
		  
            ! Umrechnung von m/s in mm/s
			tvel_ar_gr_gr(:) = tvel_ar_gr_gr(:)*1.0d3
			tvel_wk_gr_gr(:) = tvel_wk_gr_gr(:)*1.0d3
	  						
            call WK_LB_Geschwindigkeiten(id, time, iflag, no_slce_LB,	penetrtn, ctnorm, ctpoint,				
     &										rvel_ar_gr_gr, tvel_wk_ar_gr, rvel_wk_gr_gr, tdisp_wk_ar_gr,	
     &										tvel_wk_gr_gr, tvel_ar_gr_gr, rvel_wk_ar_gr, vtang_rel, vtang_sum)
          	 
! Innenring	  
        elseif (contactloc == 2) then
		
            call SPCK_AV_WXYZ(temp1, rvel_ir_gr_gr, ID_Innen, ID_Ground, ID_Ground, err)
            call SPCK_AV_VXYZ(temp1, temp1a, tvel_ir_gr_gr, ID_Innen, ID_Ground, ID_Ground, ID_Ground, err)
            call SPCK_AV_WXYZ(temp1, rvel_wk_gr_gr, ID_Waelz, ID_Ground, ID_Ground, err)
            call SPCK_AV_VXYZ(temp1, temp1a, tvel_wk_gr_gr, ID_Waelz, ID_Ground, ID_Ground, ID_Ground, err)
		  
			! Umrechnung von m/s in mm/s
			tvel_ar_gr_gr(:) = tvel_ir_gr_gr(:)*1.0d3
			tvel_wk_gr_gr(:) = tvel_wk_gr_gr(:)*1.0d3
	  
            call WK_LB_Geschwindigkeiten(id, time, iflag, no_slce_LB,	penetrtn, ctnorm, ctpoint,				
     &										rvel_ir_gr_gr, tvel_wk_ir_gr, rvel_wk_gr_gr, tdisp_wk_ir_gr,	
     &										tvel_wk_gr_gr, tvel_ir_gr_gr, rvel_wk_ir_gr, vtang_rel, vtang_sum)
                   
        endif
      		  
!---------------------------------------------------------------------------------------------
!------------------------ Berechnung der Dämpfung --------------------------------------------
!---------------------------------------------------------------------------------------------

! Mit Dämpfung
! Außenring
        if(contactloc == 1) then		
            call SPCK_AV_VXYZ(temp1, temp1a, tvel_wk_ar_gr, ID_Waelz, ID_Aussen, ID_Ground, ID_Ground, err)
            call SPCK_AV_WXYZ(temp1, rvel_wk_ar_gr, ID_Waelz, ID_Aussen, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp, tdisp_wk_wka_gr, ID_Waelz, ID_Waelz_A, ID_Ground, err)
		  
            ! Umrechnung von m/s in mm/s
            tvel_wk_ar_gr(:) = tvel_wk_ar_gr(:)*1.0d3
			
            call WK_LB_Daempfung(id, time, iflag, contactloc, no_slce_LB, slce_wdth, d_mod,                     
     &								tvel_wk_ar_gr, rvel_wk_ar_gr, tdisp_wk_wka_gr, vtang_sum,                          
     &								penetrtn, ctnorm, ctpoint, NForce,                              
     &								p_t, d_max, K0, KR, KL, KE, Keta, Kalpha, Kq, Ku, Kf, fe,       
     &								R_dash_WKAR(1:no_slce_LB), E_dash_WKAR, etaZero, alphaP,        
     &								DForce, DMoment)	
	  
	  
		! Begrenzung der Dämpfungskraft und Dämpfungsmoment			
        do i=1,no_slce_LB
	
		    if (sqrt(sum(DForce(i,:)**2.0d0)) .GT. 0.9d0*(sqrt(sum(NForce(i,:)**2.0d0))) ) then
				DForce(i,:) = 0.9d0*DForce(i,:)/(sqrt(sum(DForce(i,:)**2.0d0)))*(sqrt(sum(NForce(i,:)**2.0d0)))
	        endif
		
		    if (sqrt(sum(DMoment(i,:)**2.0d0)) .GT. 0.9d0*(sqrt(sum(NMoment(i,:)**2.0d0))) ) then
				DMoment(i,:) = 0.9d0*DMoment(i,:)/(sqrt(sum(DMoment(i,:)**2.0d0)))*(sqrt(sum(NMoment(i,:)**2.0d0)))
	        endif		
	
        enddo
							  
	    
! Innenring	 
        elseif(contactloc == 2) then		
            call SPCK_AV_VXYZ(temp1, temp1a, tvel_wk_ir_gr, ID_Waelz, ID_Innen, ID_Ground, ID_Ground, err)
            call SPCK_AV_WXYZ(temp1, rvel_wk_ir_gr, ID_Waelz, ID_Innen, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp, tdisp_wk_wka_gr, ID_Waelz, ID_Waelz_A, ID_Ground, err)
		
		! Umrechnung von m/s in mm/s
			tvel_wk_ir_gr(:) = tvel_wk_ir_gr(:)*1.0d3
		
            call WK_LB_Daempfung(id, time, iflag, contactloc, no_slce_LB, slce_wdth, d_mod,                     
     &								tvel_wk_ir_gr, rvel_wk_ir_gr, tdisp_wk_wka_gr, vtang_sum,                          
     &								penetrtn, ctnorm, ctpoint, NForce,                              
     &								p_t, d_max, K0, KR, KL, KE, Keta, Kalpha, Kq, Ku, Kf, fe,       
     &								R_dash_WKAR(1:no_slce_LB), E_dash_WKAR, etaZero, alphaP,        
     &								DForce, DMoment)	
       
		! Begrenzung der Dämpfungskraft und Dämpfungsmoment			
        do i=1,no_slce_LB
	
		    if (sqrt(sum(DForce(i,:)**2.0d0)) .GT. 0.9d0*(sqrt(sum(NForce(i,:)**2.0d0))) ) then
				DForce(i,:) = 0.9d0*DForce(i,:)/(sqrt(sum(DForce(i,:)**2.0d0)))*(sqrt(sum(NForce(i,:)**2.0d0)))
	        endif
		
		    if (sqrt(sum(DMoment(i,:)**2.0d0)) .GT. 0.9d0*(sqrt(sum(NMoment(i,:)**2.0d0))) ) then
				DMoment(i,:) = 0.9d0*DMoment(i,:)/(sqrt(sum(DMoment(i,:)**2.0d0)))*(sqrt(sum(NMoment(i,:)**2.0d0)))
	        endif		
	
        enddo

        endif
		    
				
      !if (contactloc == 1) then	
       !open(43,file='D:\DEB_Temp_Reibung.out')
       !write(43,*) DForce(:,2)
       !write(43,*)
       !write(43,*) NForce(:,2)
       !write(43,*)
       !write(43,*)
       !write(43,*)	   
       !write(43,*)
      !endif
		

			
!---------------------------------------------------------------------------------------------
!---------- Breite der Kontaktfläche pro Scheibe und maximale Pressung pro Scheibe -----------
!---------------------------------------------------------------------------------------------			   
	! Halbe Breite der Kontaktfläche pro Scheibe
		b_slce(:)=0.0d0
        if(contactloc == 1) then
            do i=1,no_slce_LB
			    if (penetrtn(i) >= prec) then
					b_slce(i)=dsqrt((8.0d0*(dsqrt(sum((NForce(i,:)+DForce(i,:))**2.0d0)))*mat_c_WKAR)/(pi*sum_rho_WKAR(i)*slce_wdth))
				    if(b_slce(i) <= prec) then
						b_slce(i)=0.0d0
				    endif
			    endif
        enddo
        elseif(contactloc == 2) then
            do i=1,no_slce_LB
			    if (penetrtn(i) >= prec) then
					b_slce(i)=dsqrt((8.0d0*(dsqrt(sum((NForce(i,:)+DForce(i,:))**2.0d0)))*mat_c_WKIR)/(pi*sum_rho_WKIR(i)*slce_wdth))
				    if(b_slce(i) <= prec) then
						b_slce(i)=0.0d0
				    endif
			    endif
            enddo
        endif

	! Maximale Pressung pro Scheibe
		p_slce(:)=0.0d0
        do i=1,no_slce_LB
            if ((penetrtn(i) >= prec) .and. (b_slce(i) >= prec)) then
				p_slce(i)=(2.0d0*dsqrt(sum((NForce(i,:)+DForce(i,:))**2.0d0)))/(Pi*b_slce(i)*slce_wdth)
			    if(p_slce(i) <= prec) then
					p_slce(i)=0.0d0
			    endif
            endif
        enddo 
			   			   			  			   
!---------------------------------------------------------------------------------------------
!---------------------------------- Reibkräfte und -momente ----------------------------------
!---------------------------------------------------------------------------------------------

! Außenring
        if(contactloc == 1) then
            call WK_LB_Reibung(id, time, iflag, no_slce_LB, no_gauss_LB,                            
     &							gfo_id_AR, gfo_id_IR,												
     &							penetrtn, ctnorm, ctpoint, pro_WK(1:no_slce_LB),					
     &							vtang_rel, vtang_sum, tdisp_wk_wka_gr,                                     
     &							race_f_mod, race_f_EHD, race_lub_mod, tau_mod,							
     &							vel_s, vel_d, mu_s, mu_d,											
     &							E_dash_WKAR, R_dash_WKAR(1:no_slce_LB), p_slce, b_slce, slce_wdth,  
     &							etaZero, alphaT, alphaP, filmT_mod, lambdaZero, alphaLambda,        
     &							lubtmp, comprT_mod, C1, C2, AV, BV, CV, DR, ER, pR,			        
     &							B, C, K, a1, a2, b1, b2, rhoZero, tauRheo,							
     &							sigma, BZH, CZH, alphaHys, scaleHys, cr, rexp,						
     &							NForce, DForce, TForce, TMoment, h0, h0th)
	 
!      call WK_LB_Reibung(id, time, iflag, no_slce_LB,                             
!     &							penetrtn, ctnorm, ctpoint, pro_WK(1:no_slce_LB),					
!     &							vtang_rel, vtang_sum, tdisp_wk_wka_gr,                                     
!     &							vel_s, vel_d, mu_s, mu_d,											
!     &							DForce, DForce, TForce, TMoment)
		
! Innenring		
        elseif(contactloc == 2) then
            call WK_LB_Reibung(id, time, iflag, no_slce_LB, no_gauss_LB,                            
     &							gfo_id_AR, gfo_id_IR,												
     &							penetrtn, ctnorm, ctpoint, pro_WK(1:no_slce_LB),					
     &							vtang_rel, vtang_sum, tdisp_wk_wka_gr,                                     
     &							race_f_mod, race_f_EHD, race_lub_mod, tau_mod,							
     &							vel_s, vel_d, mu_s, mu_d,											
     &							E_dash_WKAR, R_dash_WKAR(1:no_slce_LB), p_slce, b_slce, slce_wdth,  
     &							etaZero, alphaT, alphaP, filmT_mod, lambdaZero, alphaLambda,        
     &							lubtmp, comprT_mod, C1, C2, AV, BV, CV, DR, ER, pR,			        
     &							B, C, K, a1, a2, b1, b2, rhoZero, tauRheo,							
     &							sigma, BZH, CZH, alphaHys, scaleHys, cr, rexp,						
     &							NForce, DForce, TForce, TMoment, h0, h0th)
        endif

	
!---------------------------------------------------------------------------------------------
!--------------------------- Summation der einzelnen Kraftanteile ----------------------------
!---------------------------------------------------------------------------------------------
        
        call SPCK_AV_TRMAT (temp33, ID_Ground, ID_Waelz, err)	  !Transformationsmatrix von Wälzkörper in Grundsystem
      
        do i=1,3
			sumNForce(i)    = sum(NForce(:,i))
			sumNMoment(i)   = sum(Nmoment(:,i))
		
			sumDForce(i)    = sum(DForce(:,i))
			sumDMoment(i)   = sum(Dmoment(:,i))
			
			sumTForce(i)    = sum(TForce(:,i))
			sumTMoment(i)   = sum(TMoment(:,i))
        enddo
		
        	  	 
        do i=1,3
			result(i)	    = sumNForce(i)+sumTForce(i)+sumDForce(i)
			result(i+3)	    = sumNMoment(i)+sumTMoment(i)+sumDMoment(i)
        enddo
		
     		
        result(1:3) = matmul(temp33, result(1:3))
        result(4:6) = matmul(temp33, result(4:6))


	
!---------------------------------------------------------------------------------------------
!--------------------------- Druckausgabe ----------------------------
!---------------------------------------------------------------------------------------------	  
      !open(43,file='D:\DEB_Temp_Force1.out')
      !write(43,*) result(1:3)
	  
      !open(44,file='D:\DEB_Temp_Torque1.out')
      !write(44,*) result(4:6)
	  
        
 !       open(44,file='D:\DEB_Temp.out')
 !       call SPCK_SLV_MEASFLAG( meas_flg , ierr )
!		!Erklaerung zur Integer-Variable meas_flg:
!		!   Equations of motion are evaluated for
!		!     meas_flg = 0 : non-time domain analyses (pre-processing, linearisation, etc.)
!		!     meas_flg = 1 : time domain analyses (time integration, static equilibrium by time integration, etc.)
!		!     meas_flg = 2 : measurements
!		! hilfs_iflag = 1
!	
 !       if(meas_flg == 1) then
 !           if (contactloc == 2) then
!		
!		    write (44,'(1001f12.4)') time, h0th(20)
!		    !write (44,'(1001f12.4)') time, p_slce(:)
!		     !write (44,'(1001f12.8)') time, penetrtn(:)			
!	         !write (44,'(1001f12.4)') time, sumDForce(1:3)
!		    endif
 !       endif
	  

		 
		 
		
!---------------------------------------------------------------------------------------------
!--------------------------- Ende WÄLZKÖRPER LAUFBAHN KONTAKT---- ----------------------------
!---------------------------------------------------------------------------------------------

       endif		
	   if (contactloc == 3 .or. contactloc == 4)then		
		
!---------------------------------------------------------------------------------------------		
!---------------------------------------------------------------------------------------------
!-------------------------------------- BORDKRÄFTE (ehem. uforce23) --------------------------
!---------------------------------------------------------------------------------------------		
!---------------------------------------------------------------------------------------------	

	  
C ----------------------------------------------------------------------
C Initialisation
C ----------------------------------------------------------------------

		ierr = 0
		
		! ##### Marker #####
		ID_Aussen   	= int(par(1))    				! Marker ID Aussenring
		ID_Innen    	= int(par(2))	 				! Marker ID Innenring
		ID_Waelz   	 	= int(par(3))	 				! Marker ID Waelzkoerper
		ID_Waelz_A  	= int(par(4))    				! Marker ID Waelzkoerper-aussen
		ID_Ground  	 	= int(par(5))    				! Marker ID Ground
		ID_lb_Aussen	= int(par(89))					! Marker ID Laufbahn_Aussen
		ID_Kaefig     	= int(par(111))					! Marker ID Käfig Center
				
											
		! ##### Geometrie Parameter Wälzkörper #####		
		l_WK 			= (par(11)) !17.0 				! Länge des Wälzkörpers
		rad_wk 			= (par(91)) !22.0/2.0 			! Radius des Wälzkörpers
		WKpro_rk 		= (par(92)) !1.5 				! Kantenradius des Wälzkörpers
		
		! ##### Geometrievektor 1 (Außen- und Innenring) #####
		AR_LFB_breite 	= (par(93)) !17.4 ! Breite Laufbahn Außenring
		AR_Bord_AD 		= (par(94))!163.0	         	! Bord Außen durchmesser-Außenring
		AR_Bord_ID 		= (par(95))!156.9	         	! Bord Innen durchmesser-Außenring
		Bord_dicke 		= (par(96))!(AR_Bord_AD - AR_Bord_ID)/2.0 ! Bord Dicke-Außenring
		                         
		IR_LFB_breite 	= (par(97)) !17.4 				! Breite Laufbahn Innenring
		IR_Bord_AD 		= (par(98)) !125.1	 			! Bord Außen durchmesser-Innenring
		IR_Bord_ID 		= (par(99)) !119.0	 			! Bord Innen durchmesser-Innenring
		Bord_dicke_IR 	= (par(100)) !(IR_Bord_AD - IR_Bord_ID)/2.0 ! Bord Dicke-Innenring
		
		bord_oeffnungswinkel_IR = (par(101))				! Bord Öffnugswinkel IR
		bord_oeffnungswinkel_AR = (par(102))				! Bord Öffnugswinkel AR
		rad_teilkreis 		 = (par(103))                ! Teilkreisradius des Lagers 
			
		! ##### Vektor mit Materialdaten #####
		AR_E 			= (par(22))!2.08*(10.0**5.0)     ! Elastizitätsmodul des AR
		AR_v 			= (par(23))!0.3                  ! Querkontraktionszahl des AR
		WK_E 			= (par(24))!2.08*(10.0**5.0)     ! Elastizitätsmodul des Wälzkörpers
		WK_v 			= (par(25))!0.3	                 ! Querkontraktionszahl des Wälzkörpers
				
		! ##### Vektor mit Reibwert daten #####		
		race_f_mod_brd 		= int(par(104)) ! 2				 ! Festkörperreibmodell:	
														 ! 1 - linear  
														 ! 2 - kubisch
														 ! 3 - arctan
		vel_s_brd   		= (par(105)) !5.0				 ! Schlupfgeschwindigkeit bei mu_s_brd
		vel_d_brd			= (par(106)) !10.0			     ! Schlupfgeschwindigkeit bei mu_d_brd
		mu_s_brd			= (par(107)) !0.1				 ! mu statisch
		mu_d_brd			= (par(108)) !0.08				 ! mu dynamisch
		
		! ##### Vektor mit Dämpfungsdaten ##### 
		p_t_brd 			= (par(109)) !0.001d0	! Durchdringungstiefe max. für Materialdämpfung	
		d_max_brd 			= (par(110)) !30.0       ! Maximale Dämpfung

		
				 ! ##### Zusätzliohe Informationen für die Simulation #####
		 
		num_contacts = (par(112))


		
C ----------------------------------------------------------------------
C Execution
C ----------------------------------------------------------------------	
		!--------- Nullinitialisierung der Variablen ----------------
		dflag = 0
		prec = 0.00000001
		slce_wdth = 0.0d0
		
!---------------------------------------------------------------------------------------------
!----------------------------- WK_Bord Kontakterkenungnung -------------------------------------
!---------------------------------------------------------------------------------------------
				
        call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_wka_wk_brd, ID_Waelz, ID_Waelz_A, ID_Waelz, err)			
		! Umrechnung von m in mm
		tdisp_wk_wka_wk_brd(:) = tdisp_wk_wka_wk_brd(:)*1.0d3
	  
        if(contactloc == 3) then
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_ar_ara_ar_brd, ID_Aussen, ID_lb_Aussen, ID_Aussen, err)			
		! Umrechnung von m in mm
		tdisp_ar_ara_ar_brd(:) = tdisp_ar_ara_ar_brd(:)*1.0d3
		
        elseif(contactloc == 4) then
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_ir_ira_ir_brd, ID_Innen, ID_lb_Aussen, ID_Innen, err)				
		! Umrechnung von m in mm
		tdisp_ir_ira_ir_brd(:) = tdisp_ir_ira_ir_brd(:)*1.0d3
		
        endif
	  
		! Breite einer Scheibe
			slce_wdth = l_WK/no_slce_Bord
			
! Wälzkörper - Außenring_Bord		
        if (contactloc==3) then
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_ar_gr_brd, ID_Waelz, ID_Aussen, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_wka_gr_brd, ID_Waelz, ID_Waelz_A, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_wka_wk_brd, ID_Waelz, ID_Waelz_A, ID_Waelz, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_ar_ara_gr_brd, ID_Aussen, ID_lb_Aussen, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_ar_gr_gr_brd, ID_Aussen, ID_Ground, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_gr_gr_brd, ID_Waelz, ID_Ground, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_kf_wk_brd, ID_Waelz, ID_Kaefig, ID_Kaefig, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_ar_kf_brd, ID_Waelz, ID_Aussen, ID_Kaefig, err)
			
            call SPCK_AV_ANGLE(angles_wk_ar_brd, ID_Waelz, ID_Aussen, 3, err)
					
			! Umrechnung von m in mm
			tdisp_wk_ar_gr_brd(:) = tdisp_wk_ar_gr_brd(:)*1.0d3
			tdisp_wk_wka_gr_brd(:) = tdisp_wk_wka_gr_brd(:)*1.0d3
			tdisp_wk_wka_wk_brd(:) = tdisp_wk_wka_wk_brd(:)*1.0d3
			tdisp_ar_ara_gr_brd(:) = tdisp_ar_ara_gr_brd(:)*1.0d3
			tdisp_ar_gr_gr_brd(:) = tdisp_ar_gr_gr_brd(:)*1.0d3
			tdisp_wk_gr_gr_brd(:) = tdisp_wk_gr_gr_brd(:)*1.0d3
			tdisp_wk_kf_wk_brd(:) = tdisp_wk_kf_wk_brd(:)*1.0d3
			tdisp_wk_ar_kf_brd(:) = tdisp_wk_ar_kf_brd(:)*1.0d3
								
            call SPCK_AV_ANGLE(angles_ar_gr_brd, ID_Aussen, ID_Ground, 3, err)
			
            call SPCK_AV_WXYZ(temp1_brd, rvel_wk_ar_gr_brd, ID_Waelz, ID_Aussen, ID_Ground, err)			
            call SPCK_AV_VXYZ(temp1_brd, temp1a_brd, tvel_wk_ar_gr_brd, ID_Waelz, ID_Aussen, ID_Ground, ID_Ground, err)
						
			! Umrechnung von m/s in mm/s
            tvel_wk_ar_gr_brd(:) = tvel_wk_ar_gr_brd(:)*1.0d3
							 
           call WK_Bord_Kontakt_Ebene(time,id,iflag,dflag,contactloc,tdisp_wk_ar_gr_brd,angles_ar_gr_brd,
     &						tdisp_wk_wka_gr_brd,tdisp_wk_wka_wk_brd,tdisp_ar_ara_gr_brd,	
     &						tdisp_wk_wka_wk_brd(3),tdisp_ar_ara_ar_brd(3),								
     &						AR_LFB_breite,chord_l_a,l_wk,rad_wk,WKpro_rk,								
     &						NForce_brd,NMoment_brd,DForce_brd,DMoment_brd,penetrtn,ctnorm,ctpoint,		
     &						WK_E,WK_v,AR_E,AR_v,rvel_wk_ar_gr_brd,tvel_wk_ar_gr_brd,tdisp_ar_gr_gr_brd,
     &						tdisp_wk_gr_gr_brd,tdisp_wk_kf_wk_brd,angles_wk_ar_brd, bord_w_ar, bord_w_ir,gamma_kipp_brd,
     &						tdisp_wk_ar_kf_brd,bord_oeffnungswinkel_AR,rad_teilkreis)
     
           !open(40,file='D:\Temp_Daemp_3.out')
           !write(40,*)  gamma_kipp_brd
           !write(40,*)  bord_w_ar
           !write(40,*)  
	 
	 
C             if (abs(gamma_kipp_brd)>bord_w_ar) then
C 	          write(str,*) 'Kippwinkel großer als Bordöffnungswinkel Außenring=>Berechnung fehler', gamma_kipp_brd
C 	          call SPCK_UF_MESSAGE(MSG_WARNING, str)
C             endif
	 
! Wälzkörper - Innering_Bord		
        elseif (contactloc==4) then
			
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_ir_gr_brd, ID_Waelz, ID_Innen, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_wka_gr_brd, ID_Waelz, ID_Waelz_A, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_wka_wk_brd, ID_Waelz, ID_Waelz_A, ID_Waelz, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_ir_ira_gr_brd, ID_Innen, ID_lb_Aussen, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_ir_gr_gr_brd, ID_Innen, ID_Ground, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_gr_gr_brd, ID_Waelz, ID_Ground, ID_Ground, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_kf_wk_brd, ID_Waelz, ID_Kaefig, ID_Kaefig, err)
            call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_ir_kf_brd, ID_Waelz, ID_Innen, ID_Kaefig, err)
			
            call SPCK_AV_ANGLE(angles_wk_ir_brd, ID_Waelz, ID_Innen, 3, err)
			
			! Umrechnung von m in mm
			tdisp_wk_ir_gr_brd(:) = tdisp_wk_ir_gr_brd(:)*1.0d3
			tdisp_wk_wka_gr_brd(:) = tdisp_wk_wka_gr_brd(:)*1.0d3
			tdisp_wk_wka_wk_brd(:) = tdisp_wk_wka_wk_brd(:)*1.0d3
			tdisp_ir_ira_gr_brd(:) = tdisp_ir_ira_gr_brd(:)*1.0d3
			tdisp_ir_gr_gr_brd(:) = tdisp_ir_gr_gr_brd(:)*1.0d3
			tdisp_wk_gr_gr_brd(:) = tdisp_wk_gr_gr_brd(:)*1.0d3
			tdisp_wk_kf_wk_brd(:) = tdisp_wk_kf_wk_brd(:)*1.0d3
			tdisp_wk_ir_kf_brd(:) = tdisp_wk_ir_kf_brd(:)*1.0d3
			
            call SPCK_AV_ANGLE(angles_ir_gr_brd, ID_Innen, ID_Ground, 3, err)
			
            call SPCK_AV_WXYZ(temp1_brd, rvel_wk_ir_gr_brd, ID_Waelz, ID_Innen, ID_Ground, err)			
            call SPCK_AV_VXYZ(temp1_brd, temp1a_brd, tvel_wk_ir_gr_brd, ID_Waelz, ID_Innen, ID_Ground, ID_Ground, err)
						
			! Umrechnung von m/s in mm/s
            tvel_wk_ir_gr_brd(:) = tvel_wk_ir_gr_brd(:)*1.0d3
						
           call WK_Bord_Kontakt_Ebene(time,id,iflag,dflag,contactloc,tdisp_wk_ir_gr_brd,angles_ir_gr_brd,
     &						tdisp_wk_wka_gr_brd,tdisp_wk_wka_wk_brd,tdisp_ir_ira_gr_brd,	
     &						tdisp_wk_wka_wk_brd(3),tdisp_ir_ira_ir_brd(3),								
     &						IR_LFB_breite,chord_l_i,l_wk,rad_wk,WKpro_rk,								
     &						NForce_brd,NMoment_brd,DForce_brd,DMoment_brd,penetrtn,ctnorm,ctpoint,		
     &						WK_E,WK_v,AR_E,AR_v,rvel_wk_ir_gr_brd,tvel_wk_ir_gr_brd,tdisp_ir_gr_gr_brd,
     &						tdisp_wk_gr_gr_brd, tdisp_wk_kf_wk_brd,angles_wk_ir_brd,bord_w_ar, bord_w_ir,gamma_kipp_brd,
     &						tdisp_wk_ir_kf_brd,bord_oeffnungswinkel_IR,rad_teilkreis)
	 
	       !open(41,file='D:\Temp_Daemp_4.out')
           !write(41,*)  gamma_kipp_brd
           !write(41,*)  bord_w_ir
           !write(41,*) 
		   
C             if (abs(gamma_kipp_brd)>bord_w_ir) then
C 	          write(str,*) 'Kippwinkel großer als Bordöffnungswinkel Innering=>Berechnung fehler', gamma_kipp_brd
C 	          call SPCK_UF_MESSAGE(MSG_WARNING, str)
C             endif
        
        endif	
		 
		    !open(43,file='D:\DEB_Temp_Force1.out')
            !write(43,*) gamma_kipp_brd
	
!---------------------------------------------------------------------------------------------
!------------------------ Berechnung der Dämpfung --------------------------------------------
!---------------------------------------------------------------------------------------------	
! Linke Bord     
       if (contactloc==3) then
		
		    call SPCK_AV_WXYZ(temp1_brd, rvel_wk_ar_gr_brd, ID_Waelz, ID_Aussen, ID_Ground, err)
		    call SPCK_AV_VXYZ(temp1_brd, temp1a_brd, tvel_wk_ar_gr_brd, ID_Waelz, ID_Aussen, ID_Ground, ID_Ground, err)
		    call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_wka_gr_brd, ID_Waelz, ID_Waelz_A, ID_Ground, err)
			
			! Umrechnung von m/s in mm/s
           tvel_wk_ar_gr_brd(:) = tvel_wk_ar_gr_brd(:)*1.0d3
			
			! Umrechnung von m in mm
			tdisp_wk_wka_gr_brd(:) = tdisp_wk_wka_gr_brd(:)*1.0d3
										
		    call WK_Bord_Daempfung(id, time, iflag, contactloc, no_slce_Bord, slce_wdth,				
     &								tvel_wk_ar_gr_brd, rvel_wk_ar_gr_brd, tdisp_wk_wka_gr_brd, vtang_sum,		
     &								penetrtn, ctnorm, ctpoint, NForce_brd,			
     &								p_t, d_max, DForce_brd, DMoment_brd)

		! Begrenzung der Dämpfungskraft und Dämpfungsmoment			
        do i=1,no_slce_Bord
	
		    if (sqrt(sum(DForce_brd(i,:)**2.0d0)) .GT. 0.9d0*(sqrt(sum(NForce_brd(i,:)**2.0d0))) ) then
				DForce_brd(i,:) = 0.9d0*DForce_brd(i,:)/(sqrt(sum(DForce_brd(i,:)**2.0d0)))*(sqrt(sum(NForce_brd(i,:)**2.0d0)))
	        endif
		
		    if (sqrt(sum(DMoment_brd(i,:)**2.0d0)) .GT. 0.9d0*(sqrt(sum(NMoment_brd(i,:)**2.0d0))) ) then
				DMoment_brd(i,:) = 0.9d0*DMoment_brd(i,:)/(sqrt(sum(DMoment_brd(i,:)**2.0d0)))*(sqrt(sum(NMoment_brd(i,:)**2.0d0)))
	        endif		
	
        enddo	 

! Recht Bord 			
       elseif (contactloc==4) then
		    
		    call SPCK_AV_WXYZ(temp1_brd, rvel_wk_ir_gr_brd, ID_Waelz, ID_Innen, ID_Ground, err)
		    call SPCK_AV_VXYZ(temp1_brd, temp1a_brd, tvel_wk_ir_gr_brd, ID_Waelz, ID_Innen, ID_Ground, ID_Ground, err)
		    call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_wka_gr_brd, ID_Waelz, ID_Waelz_A, ID_Ground, err)
			
			! Umrechnung von m/s in mm/s
           tvel_wk_ir_gr_brd(:) = tvel_wk_ir_gr_brd(:)*1.0d3
			
			! Umrechnung von m in mm
			tdisp_wk_wka_gr_brd(:) = tdisp_wk_wka_gr_brd(:)*1.0d3
										
		    call WK_Bord_Daempfung(id, time, iflag, contactloc, no_slce_Bord, slce_wdth,				
     &								tvel_wk_ir_gr_brd, rvel_wk_ir_gr_brd, tdisp_wk_wka_gr_brd, vtang_sum,		
     &								penetrtn, ctnorm, ctpoint, NForce_brd,			
     &								p_t, d_max, DForce_brd, DMoment_brd)
	
		! Begrenzung der Dämpfungskraft und Dämpfungsmoment			
        do i=1,no_slce_Bord
	
		    if (sqrt(sum(DForce_brd(i,:)**2.0d0)) .GT. 0.9d0*(sqrt(sum(NForce_brd(i,:)**2.0d0))) ) then
				DForce_brd(i,:) = 0.9d0*DForce_brd(i,:)/(sqrt(sum(DForce_brd(i,:)**2.0d0)))*(sqrt(sum(NForce_brd(i,:)**2.0d0)))
	        endif
		
		    if (sqrt(sum(DMoment_brd(i,:)**2.0d0)) .GT. 0.9d0*(sqrt(sum(NMoment_brd(i,:)**2.0d0))) ) then
				DMoment_brd(i,:) = 0.9d0*DMoment_brd(i,:)/(sqrt(sum(DMoment_brd(i,:)**2.0d0)))*(sqrt(sum(NMoment_brd(i,:)**2.0d0)))
	        endif		
	
        enddo	
		
       endif
	   
        !if (contactloc == 3) then
		!    open(40,file='D:\Temp_Daemp_3.out')
		!    write(40,*)  DForce_brd(1,:)
		!    write(40,*)  DForce_brd(2,:)
		!    write(40,*)
		!    write(40,*)
        !elseif (contactloc == 4) then
		!    open(41,file='D:\Temp_Daemp_4.out')
		!    write(41,*)  DForce_brd(1,:)
		!    write(41,*)  DForce_brd(2,:)
		!    write(41,*)
		!    write(41,*)
        !endif


		
!---------------------------------------------------------------------------------------------
!------------------------ Berechnung der Relativgeschwindigkeiten im Kontakt -----------------
!---------------------------------------------------------------------------------------------
        if (contactloc==3) then
		    call SPCK_AV_WXYZ(temp1_brd, rvel_Bord_gr_gr_brd, ID_Aussen, ID_Ground, ID_Ground, err)	
		    call SPCK_AV_VXYZ(temp1_brd, temp1a_brd, tvel_wk_Bord_gr_brd, ID_Waelz, ID_Aussen, ID_Ground, ID_Ground, err)
		    call SPCK_AV_WXYZ(temp1_brd, rvel_wk_gr_gr_brd, ID_Waelz, ID_Ground, ID_Ground, err)
		    call SPCK_AV_DXYZ(temptdisp_brd, tdisp_wk_Bord_gr_brd, ID_Waelz, ID_Aussen, ID_Ground, err)
		    call SPCK_AV_VXYZ(temp1_brd, temp1a_brd, tvel_wk_gr_gr_brd, ID_Waelz, ID_Ground, ID_Ground, ID_Ground, err)
		    call SPCK_AV_VXYZ(temp1_brd, temp1a_brd, tvel_Bord_gr_gr_brd, ID_Aussen, ID_Ground, ID_Ground, ID_Ground, err)
		    call SPCK_AV_WXYZ(temp1_brd, rvel_wk_Bord_gr_brd, ID_Waelz, ID_Aussen, ID_Ground, err)
					  							
			! Umrechnung von m in mm
			tdisp_wk_Bord_gr_brd(:) = tdisp_wk_Bord_gr_brd(:)*1.0d3
			
			! Umrechnung von m/s in mm/s
			tvel_wk_Bord_gr_brd(:) = tvel_wk_Bord_gr_brd(:)*1.0d3
			tvel_wk_gr_gr_brd(:) = tvel_wk_gr_gr_brd(:)*1.0d3
			tvel_Bord_gr_gr_brd(:) = tvel_Bord_gr_gr_brd(:)*1.0d3
			
		    call WK_Bord_Geschwindigkeiten(id, time, iflag, no_slce_Bord, contactloc, penetrtn, ctnorm,        
     &                              ctpoint, rvel_Bord_gr_gr_brd, tvel_wk_Bord_gr_brd, rvel_wk_gr_gr_brd,        
     &                              tdisp_wk_Bord_gr_brd, tvel_wk_gr_gr_brd, tvel_Bord_gr_gr_brd, rvel_wk_Bord_gr_brd, 
     &                              vtang_rel, vtang_sum)
          	 					
        elseif (contactloc==4) then
						
		    call SPCK_AV_WXYZ(temp1_brd, rvel_Bord_gr_gr_brd, ID_Innen, ID_Ground, ID_Ground, err)	
		    call SPCK_AV_VXYZ(temp1_brd, temp1a_brd, tvel_wk_Bord_gr_brd, ID_Waelz, ID_Innen, ID_Ground, ID_Ground, err)
		    call SPCK_AV_WXYZ(temp1_brd, rvel_wk_gr_gr_brd, ID_Waelz, ID_Ground, ID_Ground, err)
		    call SPCK_AV_DXYZ(temptdisp_brd, tdisp_wk_Bord_gr_brd, ID_Waelz, ID_Innen, ID_Ground, err)
		    call SPCK_AV_VXYZ(temp1_brd, temp1a_brd, tvel_wk_gr_gr_brd, ID_Waelz, ID_Ground, ID_Ground, ID_Ground, err)
		    call SPCK_AV_VXYZ(temp1_brd, temp1a_brd, tvel_Bord_gr_gr_brd, ID_Innen, ID_Ground, ID_Ground, ID_Ground, err)
		    call SPCK_AV_WXYZ(temp1_brd, rvel_wk_Bord_gr_brd, ID_Waelz, ID_Innen, ID_Ground, err)
					  	
						
			! Umrechnung von m in mm
			tdisp_wk_Bord_gr_brd(:) = tdisp_wk_Bord_gr_brd(:)*1.0d3
			
			! Umrechnung von m/s in mm/s
			tvel_wk_Bord_gr_brd(:) = tvel_wk_Bord_gr_brd(:)*1.0d3
			tvel_wk_gr_gr_brd(:) = tvel_wk_gr_gr_brd(:)*1.0d3
			tvel_Bord_gr_gr_brd(:) = tvel_Bord_gr_gr_brd(:)*1.0d3
			
		    call WK_Bord_Geschwindigkeiten(id, time, iflag, no_slce_Bord, contactloc, penetrtn, ctnorm,        
     &                              ctpoint, rvel_Bord_gr_gr_brd, tvel_wk_Bord_gr_brd, rvel_wk_gr_gr_brd,        
     &                              tdisp_wk_Bord_gr_brd, tvel_wk_gr_gr_brd, tvel_Bord_gr_gr_brd, rvel_wk_Bord_gr_brd, 
     &                              vtang_rel, vtang_sum)
					
        endif
	
        !if (contactloc == 3) then
		!    open(40,file='D:\Temp_Geschw_3.out')
		!    write(40,*)  ctpoint(1,:)
		!    write(40,*)  ctpoint(2,:)
		!    write(40,*)  vtang_rel(1,:)
		!    write(40,*)  vtang_rel(2,:)
		!    write(40,*)  vtang_sum(1,:)
		!    write(40,*)  vtang_sum(2,:)
		!    write(40,*)
		!    write(40,*)
        !elseif (contactloc == 4) then
		!    open(41,file='D:\Temp_Geschw_4.out')
		!    write(41,*)  ctpoint(1,:)
		!    write(41,*)  ctpoint(2,:)
		!    write(41,*)  vtang_rel(1,:)
		!    write(41,*)  vtang_rel(2,:)
		!    write(41,*)  vtang_sum(1,:)
		!    write(41,*)  vtang_sum(2,:)
		!    write(41,*)
		!    write(41,*)
        !endif
!---------------------------------------------------------------------------------------------
!---------------------------------- Reibkräfte und -momente ----------------------------------
!---------------------------------------------------------------------------------------------
        if (contactloc==3) then	
		
		    call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_wka_gr_brd, ID_Waelz, ID_Waelz_A, ID_Ground, err)	
		
			! Umrechnung von m in mm
			tdisp_wk_wka_gr_brd(:) = tdisp_wk_wka_gr_brd(:)*1.0d3
			
		    call WK_Bord_Reibung(id, time, iflag, no_slce_Bord,						
     &                       	penetrtn, ctnorm, ctpoint,						
     &                       	vtang_rel, vtang_sum, tdisp_wk_wka_gr_brd,					
     &                       	race_f_mod, vel_s, vel_d, mu_s, mu_d,			
     &                       	NForce_brd, DForce_brd, TForce_brd, TMoment_brd)
						
        elseif (contactloc==4) then
		
		    call SPCK_AV_DXYZ( temptdisp_brd, tdisp_wk_wka_gr_brd, ID_Waelz, ID_Waelz_A, ID_Ground, err)	
		
			! Umrechnung von m in mm
			tdisp_wk_wka_gr_brd(:) = tdisp_wk_wka_gr_brd(:)*1.0d3
								
		    call WK_Bord_Reibung(id, time, iflag, no_slce_Bord,						
     &                       	penetrtn, ctnorm, ctpoint,						
     &                       	vtang_rel, vtang_sum, tdisp_wk_wka_gr_brd,					
     &                       	race_f_mod, vel_s, vel_d, mu_s, mu_d,			
     &                       	NForce_brd, DForce_brd, TForce_brd, TMoment_brd)
							
        endif

!!!!!
!! Achtung Achtung !! This part of the code has to be commented out for Lager Variants NU and NUP
!! This part of the code is valid for Lager type 'NJ'
!!!!!	
       if (num_contacts==1) then
			NForce_brd(2,:) = 0.0d0
			NMoment_brd(2,:)= 0.0d0
			DForce_brd(2,:) = 0.0d0
			DMoment_brd(2,:)= 0.0d0
			TForce_brd(2,:) = 0.0d0
			TMoment_brd(2,:)= 0.0d0
       endif
		
	
!---------------------------------------------------------------------------------------------
!--------------------------- Summation der einzelnen Kraftanteile ----------------------------
!---------------------------------------------------------------------------------------------		
		
	     !open(45,file='D:\DEB_Temp_Force.out')
         !write(45,*)  NForce_brd(1,:)
         !write(45,*)  NForce_brd(2,:)
         !write(45,*)  NMoment_brd(1,:)
         !write(45,*)  NMoment_brd(2,:)
         !write(45,*)
         !write(45,*)
		 
        call SPCK_AV_TRMAT (temp33_brd, ID_Ground, ID_Waelz, err)	  !Transformationsmatrix von Wälzkörper in Grundsystem
      
        do i=1,3
			sumNForce_brd(i)    = sum(NForce_brd(:,i))
			sumNMoment_brd(i)   = sum(NMoment_brd(:,i))
		
			sumDForce_brd(i)    = sum(DForce_brd(:,i))
			sumDMoment_brd(i)   = sum(DMoment_brd(:,i))
						
			sumTForce_brd(i)    = sum(TForce_brd(:,i))
			sumTMoment_brd(i)   = sum(TMoment_brd(:,i))
        enddo
		
        	  	 
        do i=1,3
			result_brd(i)	    = sumNForce_brd(i)+sumDForce_brd(i)+sumTForce_brd(i)
			result_brd(i+3)	    = sumNMoment_brd(i)+sumDMoment_brd(i)+sumTMoment_brd(i)
        enddo
		
        !open(46,file='D:\DEB_Temp_ohne_transform.out')
        !write(46,*)  result_brd(:)
     	!write(46,*)	
     	!write(46,*)
		
        result_brd(1:3) = matmul(temp33_brd, result_brd(1:3))
        result_brd(4:6) = matmul(temp33_brd, result_brd(4:6))
		
        !open(47,file='D:\DEB_Temp_transform.out')
        !write(47,*) result_brd(:)
     	!write(47,*)
     	!write(47,*)
		
		
!---------------------------------------------------------------------------------------------
!--------------------------- Ende BORDKRÄFTE (ehem. uforce23) --------------------------------
!---------------------------------------------------------------------------------------------	
		
		
	endif
	   if (contactloc== 5) then
	
!---------------------------------------------------------------------------------------------		
!---------------------------------------------------------------------------------------------
!-------------------------------------- Taschenfederkäfig (ehem. uforce24) -------------------
!---------------------------------------------------------------------------------------------		
!---------------------------------------------------------------------------------------------
	 ierr = 0
	  
	  ! ##### Marker #####
		ID_Waelz   	= int(par(3))    				! Marker ID Wälzkörper
		ID_Tasche   = int(par(113))	 				! Marker ID Käfig Tasche
		ID_Ground   = int(par(5))	 				! Marker ID Ground
		
	  ! ##### Parameter #####
		taschenspiel_tang = (par(114))				! Tangential Taschenspiel
		taschenspiel_rad  = (par(115))                ! Radial Taschenspiel
		taschenspiel_ax   = (par(116))				! Axial Taschenspiel
		wk_len 			  = (par(11))                ! Wälzkörper Länge
		wk_profrad 		  = (par(13))				! Profile Radius Wälzkörper
	
C ----------------------------------------------------------------------
C Execution
C ----------------------------------------------------------------------
 !--------- Nullinitialisierung der Variablen ----------------
	  dflag = 0

!------------------------------------------------------------------------
!--------------------------Auslesen von SIMPACK -------------------------
!------------------------------------------------------------------------

	! Wälzkörper - Käfig Tasche
        ! Reading relative distances from SIMPACK between Wälzkörper and Käfig Tasche
        call SPCK_AV_DXYZ(temptdisp, tdisp_wk_kfT_kfT, ID_Waelz, ID_Tasche, ID_Tasche, err)
   		
        !open(49,file='D:\DEB_Temp_results.out')
        !write(49,*) result(:)
		
		! Umrechnung von m in mm
		tdisp_wk_kfT_kfT(:) = tdisp_wk_kfT_kfT(:)*1.0d3
			  		
        !open(49,file='D:\DEB_Temp_results.out')
        !write(49,*) tdisp_wk_kfT_kfT(:)		
					
		! Reading angles of Wälzkörper from SIMPACK relative to Käfig Tasche		
        call SPCK_AV_ANGLE(angles_wk_kfT, ID_Waelz, ID_Tasche, 3, err)
		   				
		! Reading rotational and translational velocity of Wälzkörper relative to Käfig Tasche		
        call SPCK_AV_WXYZ(temp1, rvel_wk_kfT_kfT, ID_Waelz, ID_Tasche, ID_Tasche, err)			
        call SPCK_AV_VXYZ(temp1, temp1a, tvel_wk_kfT_kfT, ID_Waelz, ID_Tasche, ID_Tasche, ID_Tasche, err)

		! Umrechnung von m/s in mm/s
		tvel_wk_kfT_kfT(:) = tvel_wk_kfT_kfT(:)*1.0d3
						
        !call SPCK_UF_STEP5(vel_step1, abs(tvel_wk_kfT_kfT(1)), 0.0d0, 0.01d0, 20.0d0, 0.1d0, 0, err)
        call SPCK_UF_STEP5(vel_step1, abs(tvel_wk_kfT_kfT(1)), 0.0d0, 0.005d0, 20.0d0, 0.01d0, 0, err)
        !call SPCK_UF_STEP5(vel_step1, abs(tvel_wk_kfT_kfT(1)), 0.0d0, 0.05d0, 20.0d0, 0.1d0, 0, err)
        call SPCK_UF_STEP5(vel_step3, abs(tvel_wk_kfT_kfT(3)), 0.0d0, 0.01d0, 20.0d0, 0.1d0, 0, err)				
        call SPCK_UF_STEP5(vel_step6, abs(rvel_wk_kfT_kfT(3)), 0.0d0, 0.0d0, 1.0d0, 1.0d0, 0, err)

        call WK_KF_SpringNL(time, id, iflag, dflag, tdisp_wk_kfT_kfT, angles_wk_kfT, rvel_wk_kfT_kfT,
     &		                tvel_wk_kfT_kfT,angles_wk_kfT,taschenspiel_tang, taschenspiel_rad,taschenspiel_ax,
     &                      wk_len, wk_profrad,vel_step1,vel_step3,vel_step6, result_tfk)
    
        !open(49,file='D:\DEB_Temp_results.out')
        !write(49,*) result(:)
		      
        call SPCK_AV_TRMAT (temp33_tfk, ID_Tasche,ID_Waelz, err)	  !Transformationsmatrix von Wälzkörper in Grundsystem	// well working
	
        result_tfk(1:3) = matmul(temp33_tfk, result_tfk(1:3))
        result_tfk(4:6) = matmul(temp33_tfk, result_tfk(4:6))
		
        !open(50,file='D:\DEB_Temp_results_Transform.out')
        !write(50,*) result(:)


!---------------------------------------------------------------------------------------------
!--------------------------- Ende Taschenfederkäfig (ehem. uforce24) --------------------------------
!---------------------------------------------------------------------------------------------			
	
	endif
	   if (contactloc== 6) then
	   
	   
	   
!---------------------------------------------------------------------------------------------		
!---------------------------------------------------------------------------------------------
!-------------------------------------- Kontaktkäfig (ehem. uforce25)-------
!---------------------------------------------------------------------------------------------		
!---------------------------------------------------------------------------------------------
      ierr = 0
	  
	  mid_slce_KF 	= (no_slce_KF/2)+1   	! Index der mittleren Scheibe
	  
      ! ##### Marker ID's #####
      ID_Aussen   	= int(par(1))    		! Marker ID Aussenring Mitte 
      ID_Innen    	= int(par(2))	 		! Marker ID Innenring Mitte
      ID_Waelz    	= int(par(3))	 		! Marker ID Waelzkoerper Mitte
      ID_Waelz_A  	= int(par(4))    		! Marker ID Waelzkoerper-aussen 
      ID_Ground   	= int(par(5))    		! Marker ID Ground
	  ID_Kaefig     = int(par(117))    		! Marker Käfig
	  ID_Tasche  	= int(par(113))    		! Marker Käfig_Tasche
	  ID_KF_Aussen  = int(par(118))    		! Marker Käfig aussen (zur Kipp/Schränkwinkel-Bestimmung)
	  	  
      
	  ! ##### Geometrievektor 1(Käfig) #####  
	  KF_Ta_rad		= (par(119))/2.0  		! Käfig Taschenradius
	  prorad_KF	    =  0.0d0 				! Käfig Profilradius
	  KF_Stg_Dicke  = (par(120))        		! Dicke des Steges
	  
	  ! ##### Geometrievektor 2 (Wälzkörper) #####
	  l_WK 			= (par(11)) 	 		! Länge des Wälzkörpers
	  rad_WK 		= (par(12))/2.0     	! Radius des Wälzkörpers
	  prorad_WK 	= (par(13))         	! Profilradius
	  protype		= int(par(14))	 		!Profil Typ:	
												! 1 - zylindrisches profil
												! 2 - kreisprofil
												! 3 - din iso 281 mit geradenstück und rundungsradius
												! 4 - kreisprofil mit rundungsradius
												! 5 - zylindrisches profil mit kantenradius
	  WKpro_ap 		= (par(15)) 	    	! WK-profil nach DIN-ISO
	  WKpro_cp 		= (par(16))    	    	! WK-profil nach DIN-ISO: effektive WK-Länge
	  WKpro_dp 		= (par(17))     		! WK-profil nach DIN-ISO: Länge Zylinderstück
	  WKpro_kp 		= (par(18))         	! WK-profil nach DIN-ISO: Beginn Geradenstück
	  WKpro_rk 		= (par(19)) 	 		! WK-profil nach DIN-ISO: Kantenradius
		
     
	  ! Vektor mit Materialdaten
	  KF_E			= (par(121)) 			! Elastizitätsmodul des Käfigs
	  KF_v			= (par(122))         	! Querkontraktionszahl des Käfigs
	  WK_E			= (par(24)) 			! Elastizitätsmodul des Wälzkörpers
	  WK_v			= (par(25))         	! Querkontraktionszahl des Wälzkörpers
	  
	  ! Vektor mit Parametern für Scheibenmodell, Gauss-Integration, Curvefit
	  ctctype_KF	= int(par(128))			!	1 - Scheibenmodell AST/Tripp (höchste Genauigkeit)
											!	2 - Scheibenmodell Tripp approximiert
											!	3 - Scheibenmodell DIN ISO 281
											!   4 - Hertz !!! NOCH NICHT IMPLEMENTIERT !!!
											!   5 - Kunert (für Käfig Stahl-PA66)
	  cf_pnts_KF	= int(par(129))			! Anzahl der Curvefit-Punkte
	  cf_p_max_KF   = par(130)               ! Maximale Flächenpressung für Curvefit
	  d_mod_KF			= int(par(131))    		! Dämpfungsmodell 
	  p_t_KF			= (par(132)) 			! Durchdringungstiefe max. für Materialdämpfung
	  d_max_KF			= (par(133)) 			! Maximale Dämpfung
	  
	  ! ##### Vektor mit Reibwert- und Schmierstoffdaten #####
	  race_f_mod_KF 	= int(par(123))     		! Festkörperreibmodell:	
												! 1 - linear  
												! 2 - kubisch
												! 3 - arctan
	  vel_s_KF   	= (par(124)) 				! Schlupfgeschwindigkeit bei mu_s_KF
	  vel_d_KF		= (par(125)) 				! Schlupfgeschwindigkeit bei mu_d_KF
	  mu_s_KF		= (par(126)) 				! mu statisch
	  mu_d_KF		= (par(127)) 				! mu dynamisch
C --------------------------------------------------------------------------------------------
C Execution                                                             
C --------------------------------------------------------------------------------------------
!--------- Nullinitialisierung der Variablen ----------------

		NForce_KF = 0.0d0
		NMoment_KF = 0.0d0
		NForce2_KF = 0.0d0
		NMoment2_KF = 0.0d0
		sumNForce_KF = 0.0d0
		sumNMoment_KF = 0.0d0
		DForce_KF = 0.0d0
		DMoment_KF = 0.0d0
		DForce2_KF = 0.0d0
		DMoment2_KF = 0.0d0
		sumDForce_KF = 0.0d0
		sumDMoment_KF = 0.0d0
		TForce_KF = 0.0d0
		TMoment_KF = 0.0d0
		TForce2_KF = 0.0d0
		TMoment2_KF = 0.0d0
		sumTForce_KF = 0.0d0
		sumTMoment_KF = 0.0d0

C ---------------------------------------------------------------------------------------------
C -------------------------------------- Wälzkörperprofil -------------------------------------
C ---------------------------------------------------------------------------------------------
   
! Breite einer Scheibe
		slce_wdth_KF = l_WK/no_slce_KF

! Abstand der Scheibe i vom Wälzkörpermittelpunkt
        do i=1,no_slce_KF
			distnce_KF(i) = slce_wdth_KF*(i-0.5)-l_WK/2
        enddo

        call ProfileDetect(iflag, protype, no_slce_KF, rad_WK,                                  
     &                	l_WK, prorad_WK, distnce_KF(1:no_slce_KF), pro_WK_KF(1:no_slce_KF),
     &					WKpro_ap, WKpro_cp, WKpro_dp, WKpro_kp, WKpro_rk,           
     &					dWKpro_dEta_KF(1:no_slce_KF))

C ---------------------------------------------------------------------------------------------
C ------------------- Reduzierter Elastizitätsmodul und reduzierter Radius --------------------
C ---------------------------------------------------------------------------------------------	
						
! Reduzierter Elastizitätsmodul 
		mat_WK      = (1.0d0-WK_v**2.0d0)/WK_E
		mat_KF      = (1.0d0-KF_v**2.0d0)/KF_E
		mat_c_WKKF  = (mat_WK+mat_KF)/2.0d0
		E_dash_WKKF = 1.0d0/mat_c_WKKF
							
! Reduzierter Radius
		rho1x_WKKF(1:no_slce_KF) = 1.0d0/pro_WK_KF(1:no_slce_KF)
		rho2x_WKKF               = -1.0d0/KF_Ta_rad							
							
! Für Hertz-Kontaktmodell in Kombination mit Wälzkörperkreisprofil
        if(ctctype_KF==4 .and. protype==2) then
			rho1y_WKKF = 1.0d0/prorad_WK
			rho2y_WKKF = 1.0d0/prorad_KF
! Näherung: Linienkontakt mit nicht profilierten Kontaktpartnern
        else
			rho1y_WKKF = 0.0d0
			rho2y_WKKF = 0.0d0	        
        endif

		rhox_WKKF(1:no_slce_KF) = rho1x_WKKF(1:no_slce_KF)+rho2x_WKKF
		rhoy_WKKF               = rho1y_WKKF+rho2y_WKKF
							
        if(abs(KF_Ta_rad)>0.0d0) then
			sum_rho_WKKF(1:no_slce_KF) = rhox_WKKF(1:no_slce_KF)+rhoy_WKKF 
        else
			sum_rho_WKKF(1:no_slce_KF) = rho1x_WKKF(1:no_slce_KF)           ! Wälzkörper-Ebene Kontakt
        endif	    
		R_dash_WKKF(1:no_slce_KF) = 1.0d0/sum_rho_WKKF(1:no_slce_KF)
				
C ---------------------------------------------------------------------------------------------
C -------------------------------------- Kontakterkennung -------------------------------------
C ---------------------------------------------------------------------------------------------	
		
        call SPCK_AV_DXYZ( temptdisp_KF, tdisp375_KF, ID_Waelz, ID_Tasche, ID_Ground, err)
        call SPCK_AV_DXYZ( temptdisp_KF, tdisp345_KF, ID_Waelz, ID_Waelz_A, ID_Ground, err)
        call SPCK_AV_DXYZ( temptdisp_KF, tdisp365_KF, ID_Waelz, ID_Kaefig, ID_Ground, err)
        call SPCK_AV_DXYZ( temptdisp_KF, tdisp685_KF, ID_Kaefig, ID_KF_Aussen, ID_Ground, err)
        call SPCK_AV_DXYZ( temptdisp_KF, dist_au_WK, ID_Waelz, ID_Waelz_A, ID_Waelz, err)
        call SPCK_AV_DXYZ( temptdisp_KF, dist_au_KF, ID_Kaefig, ID_KF_Aussen, ID_Kaefig, err)
        call SPCK_AV_DXYZ( temptdisp_KF, tdisp377, ID_Waelz, ID_Tasche, ID_Tasche, err)
		
		
		! Umrechnung von m in mm
		tdisp375_KF(:) = tdisp375_KF(:)*1.0d3
		tdisp345_KF(:) = tdisp345_KF(:)*1.0d3
		tdisp365_KF(:) = tdisp365_KF(:)*1.0d3
		tdisp685_KF(:) = tdisp685_KF(:)*1.0d3
		dist_au_WK(:) = dist_au_WK(:)*1.0d3
		dist_au_KF(:) = dist_au_KF(:)*1.0d3
		tdisp377(:) = tdisp377(:)*1.0d3
		
		
        call wk_kf_kontakt_old(iflag, time, id, no_slce_KF ,					
     &							tdisp375_KF, tdisp345_KF, tdisp365_KF, tdisp685_KF,				
     &							dist_au_WK(3), dist_au_KF(3), tdisp377,					
     &							distnce_KF(1:no_slce_KF),								
     &							KF_Ta_rad,											
     &							pro_WK_KF(1:no_slce_KF), dWKpro_dEta_KF(1:no_slce_KF),	
     &							penetrtn_KF, penetrtn2_KF,								
     &							beta_schr_KF, gamma_kipp_KF,								
     &							ctnorm_KF, ctpoint2_KF,									
     &							ctnorm2_KF, ctpoint2)
				
C ---------------------------------------------------------------------------------------------
C ------------------------ Berechnung der Kontaktnormalkräfte ------------------------
C ---------------------------------------------------------------------------------------------
! Curvefit-Parameter k1 und k2 (nur für Kontaktmodell 1 - AST und 2 - Tripp)		
		
		! ########## HARDCODING ##########
		contactlocl = 1
		! ########## HARDCODING ##########
	
        call CurveFit(id, time, contactlocl, no_slce_KF, cf_pnts_KF, cf_p_max_KF,		
     &					rad_WK, KF_Ta_rad, KF_Stg_Dicke, mat_WK, mat_KF, KF_v,		
     &					mat_c_WKKF, sum_rho_WKKF(mid_slce_KF), k1_KF, k2_KF)
		
						
! Berechnung der Kontaktkraft
		!ctctype_KF = 5 ! Nach Kunert
	
        call WK_KF_Kontaktkraft(id, time, iflag, ctctype_KF, no_slce_KF, l_WK,	
     &							penetrtn_KF, ctnorm_KF, ctpoint2_KF, k1_KF, k2_KF,	
     &							NForce_KF, NMoment_KF)	
		
		
        call WK_KF_Kontaktkraft(id, time, iflag, ctctype_KF, no_slce_KF, l_WK,	
     &							penetrtn2_KF, ctnorm2_KF, ctpoint2, k1_KF, k2_KF,		
     &							NForce2_KF, NMoment2_KF)
        
			

C ---------------------------------------------------------------------------------------------
C ------------------------ Berechnung der Relativgeschwindigkeiten im Kontakt -----------------
C ---------------------------------------------------------------------------------------------
		
        call SPCK_AV_WXYZ(temp1_KF, rvel655_KF, ID_Kaefig, ID_Ground, ID_Ground, err)						
        call SPCK_AV_VXYZ(temp1_KF, temp1a_KF, tvel365_KF, ID_Waelz, ID_Kaefig, ID_Ground, ID_Ground, err)
        call SPCK_AV_WXYZ(temp1_KF, rvel355_KF, ID_Waelz, ID_Ground, ID_Ground, err)	
				
		! Umrechnung von m/s in mm/s
        tvel365_KF(:) = tvel365_KF(:)*1.0d3
				
        call WK_KF_Geschwindigkeiten(id, time, iflag, no_slce_KF,					
     &                                penetrtn_KF, ctnorm_KF, ctpoint2_KF,					
     &                                rvel655_KF, tvel365_KF, rvel355_KF, tdisp365_KF,		
     &                                vtang_rel_KF, vtang_sum_KF)
		
		
        call WK_KF_Geschwindigkeiten(id, time, iflag, no_slce_KF,				
     &								  penetrtn2_KF, ctnorm2_KF, ctpoint2,			
     &								  rvel655_KF, tvel365_KF, rvel355_KF, tdisp365_KF, 	
     &								  vtang_rel2_KF, vtang_sum2_KF)
        
	
C ---------------------------------------------------------------------------------------------
C ------------------------ Berechnung der Dämpfungskraft --------------------------------------
C ---------------------------------------------------------------------------------------------
        call SPCK_AV_WXYZ(temp1_KF, rvel365_KF, ID_Waelz, ID_Kaefig, ID_Ground, err)
        call SPCK_AV_VXYZ(temp1_KF, temp1a_KF, tvel365_KF, ID_Waelz, ID_Kaefig, ID_Ground, ID_Ground, err)
		
		! Umrechnung von m/s in mm/s
        tvel365_KF(:) = tvel365_KF(:)*1.0d3
			
        call WK_KF_Daempfung(id, time, iflag, no_slce_KF, d_mod_KF,		
     &	                       tvel365_KF, rvel365_KF,						
     &	                       penetrtn_KF, ctnorm_KF, ctpoint2_KF,				
     &	                       p_t_KF, d_max_KF, DForce_KF, DMoment_KF)

	 
        call WK_KF_Daempfung(id, time, iflag, no_slce_KF, d_mod_KF,	
     &						   tvel365_KF, rvel365_KF,					
     &						   penetrtn2_KF, ctnorm2_KF, ctpoint2,		
     &						   p_t_KF, d_max_KF, DForce2_KF, DMoment2_KF)

	 
		
		! Begrenzung der Dämpfungskraft und Dämpfungsmoment			
        do i=1,no_slce_KF
	
		    if (sqrt(sum(DForce_KF(i,:)**2.0d0)) .GT. 0.9d0*(sqrt(sum(NForce_KF(i,:)**2.0d0))) ) then
				DForce_KF(i,:) = 0.9d0*DForce_KF(i,:)/(sqrt(sum(DForce_KF(i,:)**2.0d0)))*(sqrt(sum(NForce_KF(i,:)**2.0d0)))
		    !open(43,file='D:\DEB_Temp_Cut_Dämpfung1.out')	
		    !write(43,*) 1
	        endif
		
		    if (sqrt(sum(DMoment_KF(i,:)**2.0d0)) .GT. 0.9d0*(sqrt(sum(NMoment_KF(i,:)**2.0d0))) ) then
				DMoment_KF(i,:) = 0.9d0*DMoment_KF(i,:)/(sqrt(sum(DMoment_KF(i,:)**2.0d0)))*(sqrt(sum(NMoment_KF(i,:)**2.0d0)))
		    !open(44,file='D:\DEB_Temp_Cut_Dämpfung2.out')	
		    !write(44,*) 1
	        endif		
	
        enddo
	 
        do i=1,no_slce_KF
	
		    if (sqrt(sum(DForce2_KF(i,:)**2.0d0)) .GT. 0.9d0*(sqrt(sum(NForce2_KF(i,:)**2.0d0))) ) then
				DForce2_KF(i,:) = 0.9d0*DForce2_KF(i,:)/(sqrt(sum(DForce2_KF(i,:)**2.0d0)))*(sqrt(sum(NForce2_KF(i,:)**2.0d0)))
		    !open(45,file='D:\DEB_Temp_Cut_Dämpfung3.out')	
		    !write(45,*) 1
	        endif
		
		    if (sqrt(sum(DMoment2_KF(i,:)**2.0d0)) .GT. 0.9d0*(sqrt(sum(NMoment2_KF(i,:)**2.0d0))) ) then
				DMoment2_KF(i,:) = 0.9d0*DMoment2_KF(i,:)/(sqrt(sum(DMoment2_KF(i,:)**2.0d0)))*(sqrt(sum(NMoment2_KF(i,:)**2.0d0)))
		    !open(46,file='D:\DEB_Temp_Cut_Dämpfung4.out')	
		    !write(46,*) 1
	        endif		
	
        enddo
	   

			 	  
C ---------------------------------------------------------------------------------------------
C ---------------------------------- Reibkräfte und -momente ----------------------------------
C ---------------------------------------------------------------------------------------------
		
        call WK_KF_Reibung(id, time, iflag, no_slce_KF,					
     &                        penetrtn_KF, ctnorm_KF, ctpoint2_KF, vtang_rel_KF,		
     &                        race_f_mod_KF, vel_s_KF, vel_d_KF, mu_s_KF, mu_d_KF,		
     &                        NForce_KF, DForce_KF, TForce_KF, TMoment_KF, TForce_out1_KF, vtang_rel1_mag_KF)
		
        call WK_KF_Reibung(id, time, iflag, no_slce_KF,					
     &						  penetrtn2_KF, ctnorm2_KF, ctpoint2, vtang_rel2_KF,		
     &						  race_f_mod_KF, vel_s_KF, vel_d_KF, mu_s_KF, mu_d_KF,		
     &						  NForce2_KF, DForce2_KF, TForce2_KF, TMoment2_KF, TForce_out2_KF, vtang_rel2_mag_KF)
		
		

!---------------------------------------------------------------------------------------------
!--------------------------- Summation der einzelnen Kraftanteile ----------------------------
!---------------------------------------------------------------------------------------------
        
        call SPCK_AV_TRMAT (temp33_KF, ID_Ground, ID_Waelz, err)	  !Transformationsmatrix von Wälzkörper in Grundsystem
      
        do i=1,3
			sumNForce_KF(i)    = sum(NForce_KF(:,i))+ sum(NForce2_KF(:,i))
			sumNMoment_KF(i)   = sum(NMoment_KF(:,i))+ sum(NMoment2_KF(:,i))	
			
			sumDForce_KF(i)	= sum(DForce_KF(:,i))+ sum(DForce2_KF(:,i))
		    sumDMoment_KF(i)	= sum(DMoment_KF(:,i))+ sum(DMoment2_KF(:,i))		
			
			sumTForce_KF(i)	= sum(TForce_KF(:,i)) + sum(TForce2_KF(:,i))
			sumTMoment_KF(i)	= sum(TMoment_KF(:,i)) + sum(TMoment2_KF(:,i))			
        enddo
					
        	  	 
        do i=1,3
			result_KF(i)	    = sumNForce_KF(i)+ sumDForce_KF(i) + sumTForce_KF(i)
			result_KF(i+3)	    = sumNMoment_KF(i)+ sumDMoment_KF(i) + sumTMoment_KF(i)
        enddo
		

		
        result_KF(1:3) = matmul(temp33_KF, result_KF(1:3))
        result_KF(4:6) = matmul(temp33_KF, result_KF(4:6))	  

!---------------------------------------------------------------------------------------------
!--------------------------- Ende Kontaktkäfig (ehem. uforce25) --------------------------------
!---------------------------------------------------------------------------------------------		
	  endif
	   if (contactloc== 7) then
	   
!---------------------------------------------------------------------------------------------		
!---------------------------------------------------------------------------------------------
!--------------------------------------  Führungsbord (ehem. uforce26)-------
!---------------------------------------------------------------------------------------------		
!---------------------------------------------------------------------------------------------
C ----------------------------------------------------------------------
C Initialisation
C ----------------------------------------------------------------------
      ierr = 0
	    
      ! ##### Marker ID's #####
      ID_KF_Bord   	  = int(par(134))    		
      ID_Bord    	  = int(par(135))	 		
      ID_Ground    	  = int(par(5))
	  kaefigmod   	  = int(par(136))
	  
	  
	  ar_bord_rad     = par(95)/2.0       ! Bordradius AR
	  ar_youngs	      = par(22)		    ! Elastizitätsmodul des AR
	  ar_poisson	  = par(23)		    ! Querkontraktionszahl des AR
	  
	  ir_bord_rad     = par(98)/2.0       ! Bordradius IR
	  ir_youngs	      = par(20)		    ! Elastizitätsmodul des IR
	  ir_poisson	  = par(21)		    ! Querkontraktionszahl des IR
      
	  kf_innenrad     = par(138)/2.0       ! Innenradius Käfig
	  kf_aussenrad    = par(137)/2.0       ! Aussenradius Käfig
	  kf_youngs		  = par(121)			! Elastizitätsmodul Käfig
	  kf_poisson	  = par(122)		    ! Querkontraktionszahl Käfig
	  kf_steg_br      = par(120)           ! Stegbreite Käfig
  	  kf_d_max		  = par(133)		    ! Maximale Dämpfung
	  kf_p_t		  = par(132)		    ! Durchdringungstiefe max. für Materialdämpfung

      etazero_FB		  = 81.101652
      gfo_id          = 2  
	  prec			  = 0.00000001
	  	  
      
C --------------------------------------------------------------------------------------------
C Execution                                                             
C --------------------------------------------------------------------------------------------
!--------- Nullinitialisierung der Variablen ----------------

		kf_NForce  = 0.0d0
		kf_DForce  = 0.0d0
		kf_TForce  = 0.0d0
		kf_TMoment = 0.0d0

C ---------------------------------------------------------------------------------------------
C -------------------------------------- Käfigführungsbord Berechnung--------------------------
C ---------------------------------------------------------------------------------------------
! (Typ MA, außenbordgeführt)
! (Typ MB, innenbordgeführt)  
   
   
	        if (kaefigmod==5 .or. kaefigmod==6)   then
			
                kf_prec = prec
				! Auslesen der Positionen zur Kontakterkennung		  
                call SPCK_AV_DXYZ(temptdisp_FB, tdisp788_FB, ID_KF_Bord, ID_Bord, ID_Bord, err)
                tdisp788_FB(:)=  tdisp788_FB(:)*1d3
				         
		        !call SPCK_AV_DXYZ(temp_FB, tdisp785_FB, ID_KF_Bord, ID_Bord, ID_Ground, err)
                !tdisp785_FB(:)=  tdisp785_FB(:)*1d3
		  			
                !call SPCK_AV_DXYZ(temp_FB, tdisp755_FB, ID_KF_Bord, ID_Ground, ID_Ground, err)
                !tdisp755_FB(:)=  tdisp755_FB(:)*1d3
		 		  
				!!! KOS Tasche --> KOS Referenz
				! Erstellen der Transformationsmatrix LB_j-ground
                call SPCK_AV_TRMAT( transf_LB_ref_FB, ID_Bord, ID_Ground, err) !dc58 
                call SPCK_AV_WXYZ(temp1_FB, rvel785_FB, ID_KF_Bord, ID_Bord, ID_Ground, err)
                call SPCK_AV_VXYZ(temp_FB, temp1_FB, tvel785_FB, ID_KF_Bord, ID_Bord, ID_Ground, ID_Ground, err)
                tvel785_FB(:)=tvel785_FB(:)*1.0d3
							
                call SPCK_AV_TRMAT (temp33_FB, ID_Ground, ID_KF_Bord, err)
				
				! Abstand Mittelpunkt Käfigbord - Mittelpunkt Außenringbord		
				exzent		= sqrt(tdisp788_FB(1)**2+tdisp788_FB(2)**2)		! [mm]
				
				!call SPCK_UF_STEP5(vel_step1, abs(tvel_wk_kfT_kfT(1)), 0.0d0, 0.005d0, 20.0d0, 0.01d0, 0, err)
				call SPCK_UF_STEP5(delta, exzent, 0.0d0, 0.0d0, kf_p_t, kf_d_max, 0, err)
						
                if (kaefigmod==5) then
                    call KF_Bord(time,id,kf_prec,tdisp788_FB,transf_LB_ref_FB,rvel785_FB,kaefigmod,tvel785_FB,exzent,
     &									delta, kf_aussenrad, kf_innenrad, KF_steg_br, IR_Bord_rad, AR_Bord_rad,		
     &									ar_poisson, ar_youngs, ir_poisson, ir_youngs, kf_poisson, kf_youngs, etazero_FB,
     &									kf_d_max, kf_p_t, kf_NForce, kf_DForce, kf_TForce, kf_TMoment)
     			        
                elseif (kaefigmod==6) then
				    call KF_Bord(time,id,kf_prec,tdisp788_FB,transf_LB_ref_FB,rvel785_FB,kaefigmod,tvel785_FB,exzent,
     &									delta, kf_aussenrad, kf_innenrad, KF_steg_br, IR_Bord_rad, AR_Bord_rad,		
     &									ar_poisson, ar_youngs, ir_poisson, ir_youngs, kf_poisson, kf_youngs, etazero_FB,
     &									kf_d_max, kf_p_t, kf_NForce, kf_DForce, kf_TForce, kf_TMoment)
     			        			
                endif
			
!			    if(gfo_id==1) then
!				    open(41,file='D:\DEB_Temp_1.out')
!			        write(41,*) rvel785_FB(:)	
!			        write(41,*) tvel785_FB(:)
!			        write(41,*)
!			        write(41,*)
!		        endif
!				
!		        if(gfo_id==2) then
!			        open(42,file='D:\DEB_Temp_2.out')
!				    write(42,*) rvel785_FB(:)	
!			        write(42,*) tvel785_FB(:)
!			        write(42,*)
!			        write(42,*)					
!		        endif
			
								
      			result_FB(1:3) = kf_NForce(:)+kf_DForce(:)+kf_TForce(:)
      			result_FB(1:3) = matmul(temp33_FB, result_FB(1:3))
      			result_FB(4:6) = kf_TMoment(:)
      			result_FB(4:6) = matmul(temp33_FB, result_FB(4:6))
				
			endif


!---------------------------------------------------------------------------------------------
!--------------------------- Ende Führungsbord (ehem. uforce26) --------------------------------
!---------------------------------------------------------------------------------------------	
	   endif
	
C ----------------------------------------------------------------------
C task = 0 : Determine force, torque and output values
C ----------------------------------------------------------------------

      if ( task .eq. 0 ) then
		if (contactloc == 1 .or. contactloc == 2)then	
			! initialise outputs
			force(1) = result(1)
			force(2) = result(2)
			force(3) = result(3)
			torque(1)= result(4)/1.0d3
			torque(2)= result(5)/1.0d3
			torque(3)= result(6)/1.0d3
			

			
	!		! Outputvalues
					ov(1)=force(1)
					ov(2)=force(2)
					ov(3)=force(3)
					ov(4)=sqrt(force(1)**2+force(2)**2+force(3)**2)
					ov(5)=sumDForce(1)
					ov(6)=sumDForce(2)
					ov(7)=sumDForce(3)
					ov(8)=sqrt( sumDForce(1)**2+sumDForce(2)**2+sumDForce(3)**2 )
					ov(9)=sumTForce(1)
					ov(10)=sumTForce(2)
					ov(11)=sumTForce(3)
					ov(12)=sqrt( sumTForce(1)**2+sumTForce(2)**2+sumTForce(3)**2 )
					ov(13)=sumTMoment(1)
					ov(14)=sumTMoment(2)
					ov(15)=sumTMoment(3)
					ov(16)=sqrt( sumTMoment(1)**2+sumTMoment(2)**2+sumTMoment(3)**2)
		!	
		!
		!			h0th = h0th_sl(mid_slce)

					ov(17)=h0th(mid_slce)*1.0d3


			

			 stdynd(1:stdyn_dim) = 0.0d0
		
		else if (contactloc == 3 .or. contactloc == 4)then	 
							 ! initialise outputs
			force(1) = result_brd(1)
			force(2) = result_brd(2)
			force(3) = result_brd(3)
			torque(1)= result_brd(4)/1.0d3
			torque(2)= result_brd(5)/1.0d3
			torque(3)= result_brd(6)/1.0d3
			 
			! Outputvalues
					ov(1)=force(1)
					ov(2)=force(2)
					ov(3)=force(3)
					ov(4)=sqrt(force(1)**2+force(2)**2+force(3)**2)
					ov(5)=sumDForce_brd(1)
					ov(6)=sumDForce_brd(2)
					ov(7)=sumDForce_brd(3)
					ov(8)=sqrt( sumDForce_brd(1)**2+sumDForce_brd(2)**2+sumDForce_brd(3)**2 )
					ov(9)=sumTForce_brd(1)
					ov(10)=sumTForce_brd(2)
					ov(11)=sumTForce_brd(3)
					ov(12)=sqrt( sumTForce_brd(1)**2+sumTForce_brd(2)**2+sumTForce_brd(3)**2 )
					ov(13)=sumTMoment_brd(1)
					ov(14)=sumTMoment_brd(2)
					ov(15)=sumTMoment_brd(3)
					ov(16)=sqrt( sumTMoment_brd(1)**2+sumTMoment_brd(2)**2+sumTMoment_brd(3)**2)        
			 
		
			 
					 

			 stdynd(1:stdyn_dim) = 0.0d0

		else if  (contactloc == 5) then
					! initialise outputs
			force(1) = result_tfk(1)
			force(2) = result_tfk(2)
			force(3) = result_tfk(3)
			torque(1)= result_tfk(4)/1.0d3
			torque(2)= result_tfk(5)/1.0d3
			torque(3)= result_tfk(6)/1.0d3
					   

			
			 ov(1:ov_dim)        = 0.0d0
			 stdynd(1:stdyn_dim) = 0.0d0

		 else if (contactloc == 6) then
		 
						  ! initialise outputs
				 force(1) = result_KF(1)
				 force(2) = result_KF(2)
				 force(3) = result_KF(3)
				 torque(1)= result_KF(4)/1.0d3
				 torque(2)= result_KF(5)/1.0d3
				 torque(3)= result_KF(6)/1.0d3
							  
				 ov(1:ov_dim)        = 0.0d0
				 stdynd(1:stdyn_dim) = 0.0d0
		 
		 else if (contactloc==7) then
		 		force(1)=result_FB(1)
				force(2)=result_FB(2)
				force(3)=result_FB(3)
				torque(1)=result_FB(4)/1.0d3
				torque(2)=result_FB(5)/1.0d3
				torque(3)=result_FB(6)/1.0d3


				
				ov(1)=kf_NForce(1)
				ov(2)=kf_NForce(2)
				ov(3)=kf_NForce(3)
				ov(4)=sqrt( kf_NForce(1)**2+kf_NForce(2)**2+kf_NForce(3)**2 )
				ov(5)=kf_DForce(1)
				ov(6)=kf_DForce(2)
				ov(7)=kf_DForce(3)
				ov(8)=sqrt( kf_DForce(1)**2+kf_DForce(2)**2+kf_DForce(3)**2 )
				ov(9)=kf_TForce(1)
				ov(10)=kf_TForce(2)
				ov(11)=kf_TForce(3)
				ov(12)=sqrt( kf_TForce(1)**2+kf_TForce(2)**2+kf_TForce(3)**2 )

		
		 ov(1:ov_dim)        = 0.0d0
         stdynd(1:stdyn_dim) = 0.0d0
		 
		 endif

C ----------------------------------------------------------------------
C task = 1 : Evaluate root functions
C ----------------------------------------------------------------------

      else if ( task .eq. 1 ) then

         ! initialise outputs
         valroot(1:stroot_dim)  = 0.0d0

C ----------------------------------------------------------------------
C task = 2 : Perform state reset after root state switch
C ----------------------------------------------------------------------

      else if ( task .eq. 2 ) then

         continue

C ----------------------------------------------------------------------
C task = 3 : Determine algebraic state residuals
C ----------------------------------------------------------------------

      else if ( task .eq. 3 ) then

         continue

C ----------------------------------------------------------------------
C task = 4 : Initialise states after calculation of consistent states
C ----------------------------------------------------------------------

      else if ( task .eq. 4 ) then

         continue

C ----------------------------------------------------------------------
C task = 5 : Initialisation (first call)
C ----------------------------------------------------------------------

      else if ( task .eq. 5 ) then

         continue

C ----------------------------------------------------------------------
C task = 6 : Finalisation (last call)
C ----------------------------------------------------------------------

      else if ( task .eq. 6 ) then

         continue

      end if

C ----------------------------------------------------------------------
C Error Handling and Return
C ----------------------------------------------------------------------


C ----------------------------------------------------------------------
C automatisierte Ausgabe
C ----------------------------------------------------------------------
		!ausgabe = 1
!ausgabe 	0: keine Ausgabe - 1: Einzellager - 2: Lagersystem
       if (contactloc == 1 .or. contactloc == 2) then
	     if (ausgabe == 1 .or. ausgabe == 2) then	
			i_wk = 0.0d0
	  
			! Grundl. Parameter zur Erst. von Lastkollektiven für Workbench - Lager2
			! --> Lebensdauerberechnung
			!	Variante	|			Vorgabe			|	Berechnung				|
			!		1		| Verschiebung, Verkippung	| Kraft, Moment				|
			!		2		| Verkippung, Kraft			| Moment, Verschiebung		|
			!		3		| Verschiebung, Moment		| Kraft, Verkippung			|
			!		4		| Kraft, Moment				| Verschiebung, Verkippung	|
			!		5		| DZ, Verkippung, FX, FY	| FZ, Moment, DX, DY		|
			
			verschiebung211 = 0.0d0
			verkippung211	= 0.0d0
			kraft255		= 0.0d0
			moment255		= 0.0d0
			kraft155		= 0.0d0
			moment155		= 0.0d0
			kraft455		= 0.0d0
			moment455		= 0.0d0
			
			! Verschiebung/Verkippung IR zu AR
		    call SPCK_AV_DXYZ( temptdisp, verschiebung211, ID_Innen, ID_Aussen, ID_Aussen, err)
		    call SPCK_AV_ANGLE( temptdisp, verkippung211, ID_Innen, ID_Aussen, ID_Aussen, err)
			! Kraft/Moment auf IR
		    call SPCK_AV_FXYZ( temptdisp, kraft255, ID_Innen, ID_Ground, ID_Ground, err)
		    call SPCK_AV_TXYZ( temptdisp, moment255, ID_Innen, ID_Ground, ID_Ground, err)
			! Kraft/Moment auf AR
		    call SPCK_AV_FXYZ( temptdisp, kraft155, ID_Aussen, ID_Ground, ID_Ground, err)
		    call SPCK_AV_TXYZ( temptdisp, moment155, ID_Aussen, ID_Ground, ID_Ground, err)

		
			! Bestimmen der Verlagerung/Rotationsgeschw. von AR,IR,KF bzgl. GR
			tdisp155 = 0.0d0
			tdisp255 = 0.0d0
			tdisp455 = 0.0d0
			rdisp355 = 0.0d0
			rdisp455 = 0.0d0
			rdisp155 = 0.0d0
			rdisp255 = 0.0d0
			tdisp311_ausgabe = 0.0d0
	  
		    call SPCK_AV_DXYZ( temptdisp, tdisp155, ID_Aussen, ID_Ground, ID_Ground, err)
		    call SPCK_AV_DXYZ( temptdisp, tdisp255, ID_Innen, ID_Ground, ID_Ground, err)		    
		    call SPCK_AV_WXYZ( temptdisp, rdisp355, ID_Waelz, ID_Ground, ID_Ground, err)
		    call SPCK_AV_WXYZ( temptdisp, rdisp155, ID_Aussen, ID_Ground, ID_Ground, err)
		    call SPCK_AV_WXYZ( temptdisp, rdisp255, ID_Innen, ID_Ground, ID_Ground, err)
		    call SPCK_AV_DXYZ( temptdisp, tdisp311_ausgabe, ID_Waelz, ID_Aussen, ID_Aussen, err)
		
		    call SPCK_SLV_MEASFLAG( meas_flg , ierr )
			! Erklaerung zur Integer-Variable meas_flg:
			!   Equations of motion are evaluated for
			!     meas_flg = 0 : non-time domain analyses (pre-processing, linearisation, etc.)
			!     meas_flg = 1 : time domain analyses (time integration, static equilibrium by time integration, etc.)
			!     meas_flg = 2 : measurements
			! hilfs_iflag = 1
	  
		    if (meas_flg == 0) then
			    if (hilfs_iflag == 1) then
				    call date_and_time(date_time_b(1), date_time_b(2), date_time_b(3), date_time)
						! Erklaerung zu den Variablen der Zeiterfassung:
						! integer - Variablen:  
						! 	date_time(1) : Jahr
						!	date_time(2) : Monat
						!	date_time(3) : Tag
						!	date_time(4) : Zeitdifferenz in min
						!	date_time(5) : Stunde
						!   date_time(6) : Minute
						!   date_time(7) : Sekunde
						!   date_time(8) : Millisekunde
						! char - Variablen:  
						!   date_time_b(1) : Datum
						!   date_time_b(2) : Zeit
						!   date_time_b(3) : Zeitzone
					datum = date_time_b(1)
					uhrzeit = date_time_b(2)
			
			
					anz_parallelSim_int = 1.0     ! Number of inovocations of Routine
				    write(anz_parallelSim_char,'(I5.1)') anz_parallelSim_int
					anz_parallelSim_char = adjustl(anz_parallelSim_char)
			
			
					! Festlegen/Erstellen des Ausgabeordners
				
					! *****************************************************************
					! *** Automatische PFADANGABE
					! *****************************************************************
				    call SPCK_UF_OutputPath(ausgabepfad, l_ausgabepfad, err)

9887				ausgabeindex = index(ausgabepfad,'/')
				    if (ausgabeindex /= 0) then	 
						ausgabepfad_ende = ausgabepfad((ausgabeindex+1):len(ausgabepfad))
						ausgabepfad_anfang = ausgabepfad(1:ausgabeindex-1)
						ausgabepfad = trim(ausgabepfad_anfang)//'\\'//trim(ausgabepfad_ende)
						goto 9887
				    endif
					!ausgabepfad = ausgabepfad
					path = trim(ausgabepfad)
				
					folder = trim(datum) //'_'// trim(uhrzeit(1:4))
					makedirectory = 'mkdir -p ' // trim(path) // trim(folder) 
				    call system(makedirectory)

					! Erhöhung der Simulationsnr. um eins, falls es parallel laufende Simulationen gibt (gleicher hh:mm-Ordner)
 9901				inquire(file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)// 
     &   '__Simulationsbeginn.txt', EXIST=ordnerabfrage)	
				    if(ordnerabfrage==.true.) then
						anz_parallelSim_int = anz_parallelSim_int + 1.0
						write(anz_parallelSim_char,'(I5.1)') anz_parallelSim_int
						anz_parallelSim_char = adjustl(anz_parallelSim_char)
						goto 9901
				    endif
			
				    open(1030,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)// '__Simulationsbeginn.txt')
				    write(1030,*) 'Die zugehörige Simulation der in diesem Ordner hinterlegten Ergebnisdateien'
				    write(1030,*) 'wurde am ',datum(7:8),'.',datum(5:6),'.',datum(1:4),' um ',uhrzeit(1:2),':',
     &	uhrzeit(3:4),':',uhrzeit(5:6),' Uhr gestartet.'
				    write(1030,*) ' '
				
			    endif

				hilfs_iflag = hilfs_iflag + 1
		    endif

		    if (meas_flg==2) then 
							
			! Über den Ground-Marker (ID_Ground) wird auf das zugehörige Lager geschlossen.
			! Hierfür wird anhand der ID der Markername in der Form $LAGERNAME.$M_GROUND/$M_Isys ausgelesen.
			! Anschließend wird der Name auf den LAGERNAMEN gekürzt.
			    call SPCK_AN_MARKERID2NAME(marker_element_name, marker_element_name_length, ID_Ground, err) 
			
			    if (ausgabe == 1) then		! Einzellager
					marker_element_name = trim(marker_element_name)
					marker_element_name_length = len_trim(marker_element_name)
					marker_element_name = marker_element_name(2:(marker_element_name_length))
				
			    else if (ausgabe == 2) then	! Lagersystem
					marker_element_name = trim(marker_element_name)
					marker_element_name_length = len_trim(marker_element_name)
				    if (contactloc == 1 .or. contactloc == 2) then
					    marker_element_name = marker_element_name(2:(marker_element_name_length-8)) ! "Abzug von .$M_Isys" - daher 8
				    elseif (contactloc == 3) then
					    marker_element_name = marker_element_name(2:(marker_element_name_length-10)) ! "Abzug von .$M_GROUND" - daher 10
				    endif
					marker_element_name_length = len_trim(marker_element_name)
			    endif
			
				lagernr = 1
			    if (ausgabe == 2) then
				    if(lager_iflag == 1) then
						lager_vektor(:) = ''
						lager_vektor(lagernr) = trim(marker_element_name)
						lager_iflag = lager_iflag + 1
				    endif
					! Erkl.: lager_vektor(1) = Aussen ID von Lager 1
				
9982				if (trim(marker_element_name) /= trim(lager_vektor(lagernr))) then
						lagernr = lagernr + 1
						if (trim(lager_vektor(lagernr)) == '') then
							lager_vektor(lagernr) = trim(marker_element_name)
						endif
						goto 9982
				    endif	
			    endif				
			
			    open(1235468,file= trim(path) // 'tmp.txt')
			    write(1235468,*) time , ' ', ID_Ground, ID_Aussen, lagernr, ' ', contactloc, lager_vektor
			
			    call SPCK_AN_FORCEID2NAME(force_element_name, force_element_name_length, id, err)
			    if (ausgabe == 1) then	 	! Einzellager		
					force_element_name = trim(force_element_name)
					force_element_name_length = len_trim(force_element_name)
					force_element_name = force_element_name(2:(force_element_name_length))
			    else if (ausgabe == 2) then	! Lagersystem
					force_element_name = trim(force_element_name)
					force_element_name_length = len_trim(force_element_name)
					force_element_name = force_element_name((marker_element_name_length+4):(force_element_name_length))
			    endif
			
			!START: TFR 19.04.13
			    if (ausgabe == 1) then	 	! Einzellager		
					jointARname = '$J_Aussenring'
					jointIRname = '$J_Innenring'
			    else if (ausgabe == 2) then	! Lagersystem
					jointARname = '$'//trim(marker_element_name)//'.$J_Aussenring'
					jointIRname = '$'//trim(marker_element_name)//'.$J_Innenring'
			    endif
			
			    if (contactloc == 1) then
				    call SPCK_AN_JOINTNAME2ID(ID_JointAR, jointARname, err)
				! Constraint Forces of Joint - AR
				    call SPCK_AV_JOINTCF(CFJointAR, ID_JointAR, 0, 0, ID_Ground, err)
			    else if (contactloc == 2) then
				    call SPCK_AN_JOINTNAME2ID(ID_JointIR, jointIRname, err)
				! Constraint Forces of Joint - IR
				    call SPCK_AV_JOINTCF(CFJointIR, ID_JointIR, 0, 0, ID_Ground, err)
			    endif
		
		

			    open(7781,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)//'_info.txt')
			    write(7781,*) time,' ',lagernr,' ',contactloc ,' ',trim(marker_element_name),' ',trim(force_element_name)
			
				dateinr = 2000.0d0 + lagernr*100.0d0
			
			    if (contactloc == 1) then
			
				    open(dateinr+1,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)//'_RESULT_displ_AR_GR_GR.txt')
				    write(dateinr+1,'(1001e18.6)') tdisp155
				    open(dateinr+8,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)//'_RESULT_Force_AR.txt')
				    write(dateinr+8,'(1001e18.6)') kraft155
				    open(dateinr+9,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)//'_RESULT_Torque_AR.txt')
				    write(dateinr+9,'(1001e18.6)') moment155
				
				    open(dateinr+15,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)//'_RESULT_CFJointAR.txt')
				    write(dateinr+15,'(1001e18.6)') CFJointAR
				! SPCK_AV_JOINTCF -> (1) Fmag, (2-4) Fx,Fy,Fz, (5) Tmag, (6-8) Tx,Ty,Tz
				
			    else if (contactloc == 2) then
			
				    open(dateinr+2,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)//'_RESULT_displ_IR_GR_GR.txt')
				    write(dateinr+2,'(1001e18.6)') tdisp255
				    open(dateinr+3,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)//'_RESULT_rotgeschw_IR_GR_GR.txt')
				    write(dateinr+3,'(1001e18.6)') rdisp255
				    open(dateinr+4,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)//'_RESULT_displ_IR_AR_AR.txt')
				    write(dateinr+4,'(1001e18.6)') verschiebung211
				    open(dateinr+5,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)//'_RESULT_kipp_IR_AR_AR.txt')
				    write(dateinr+5,'(1001e18.6)') verkippung211
				    open(dateinr+6,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)//'_RESULT_Force_IR.txt')
				    write(dateinr+6,'(1001e18.6)') kraft255
				    open(dateinr+7,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)//'_RESULT_Torque_IR.txt')
				    write(dateinr+7,'(1001e18.6)') moment255
				
				    open(dateinr+14,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)//'_RESULT_CFJointIR.txt')
				    write(dateinr+14,'(1001e18.6)') CFJointIR
				! SPCK_AV_JOINTCF -> (1) Fmag, (2-4) Fx,Fy,Fz, (5) Tmag, (6-8) Tx,Ty,Tz
					
			    endif
			! !ENDE: TFR 19.04.13
			
			
			
				! !START: TFR 22.04.13
				nAR = rdisp155(3)
				nIR = rdisp255(3)
				nKF_ideal = (1.0d0-(2.0d0*rad_WK/(rad_AR+rad_IR)))*(nIR/2.0d0)+
     &                      (1.0d0+(2.0d0*rad_WK/(rad_AR+rad_IR)))*(nAR/2.0d0)
				nWK_ideal = (((rad_AR+rad_IR)**2.0d0-4.0d0*rad_WK**2.0d0)/((rad_AR+rad_IR)*2.0d0*rad_WK))*((nAR-nIR)/2.0d0)
			! !ENDE: TFR 22.04.13
			
			
				dateinr = lagernr*100000 + contactloc*10000 + ID_Waelz*100
			! Erklärung: "einzigartige" Dateinummer über Zusammensetzung aus contactloc und ID_Waelz
	
			! Ausgabe der Verlagerung von AR,IR,KF bzgl. GR
			! Achtung: Die Datei wird für jeden WK neu beschrieben!
			    open(1040,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_RESULT_time.txt')
			    write(1040,'(1001e18.6)') time
			
			    open(dateinr+21,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)// '_RESULT_nWK_ideal.txt')
			    write(dateinr+21,'(1001e18.6)') nWK_ideal
			
			    open(dateinr+22,file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'//
     &											trim(marker_element_name)// '_RESULT_nKF_ideal.txt')
			    write(dateinr+22,'(1001e18.6)') nKF_ideal
			
			    if (contactloc == 1 .OR. contactloc == 2) then
			
				    open((dateinr+1),file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'
     &											//trim(marker_element_name)//'_RESULT_pH_'//trim(force_element_name)//'.txt')
				    write((dateinr+1),'(1001e18.6)') p_slce

				    open((dateinr+2),file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'
     &											//trim(marker_element_name)//'_RESULT_bH_'//trim(force_element_name)//'.txt')
				    write((dateinr+2),'(1001e18.6)') b_slce
				    open((dateinr+3),file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'
     &											//trim(marker_element_name)//'_RESULT_distnce_'//trim(force_element_name)//'.txt')
				    write((dateinr+3),'(1001e18.6)') distnce
				    open((dateinr+4),file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'
     &											//trim(marker_element_name)//'_RESULT_h0_'//trim(force_element_name)//'.txt')
				    write((dateinr+4),'(1001e18.6)') h0(1:no_slce_LB)
				

				    open((dateinr+8),file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'
     &											//trim(marker_element_name)//'_RESULT_pm_'//trim(force_element_name)//'.txt')
				    write((dateinr+8),'(1001e18.6)') ov(32)
				    open((dateinr+9),file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'
     &											//trim(marker_element_name)//'_RESULT_h0m_'//trim(force_element_name)//'.txt')
				    write((dateinr+9),'(1001e18.6)') ov(17)
				    open((dateinr+10),file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'
     &											//trim(marker_element_name)//'_RESULT_FN_abs_'//trim(force_element_name)//'.txt')
				    write((dateinr+10),'(1001e18.6)') ov(4)
			    else
				
			    endif
				
			    if (contactloc == 1) then
				    open((dateinr+6),file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'
     &											//trim(marker_element_name)//'_RESULT_rotgeschw_WK_GR_GR_'//trim(force_element_name)//'.txt')
				    write((dateinr+6),'(1001e18.6)') rdisp355
				    open((dateinr+7),file= trim(path) // trim(folder) // '\\_Sim' //trim(anz_parallelSim_char)//'_'
     &											//trim(marker_element_name)//'_RESULT_displ_WK_AR_AR_'//trim(force_element_name)//'.txt')
				    write((dateinr+7),'(1001e18.6)') tdisp311_ausgabe

			    endif
		    endif
	     endif
       endif


C ----------------------------------------------------------------------
C Ende: autom. Ausgabe
C ----------------------------------------------------------------------	

      return

      end subroutine

	  
	  
