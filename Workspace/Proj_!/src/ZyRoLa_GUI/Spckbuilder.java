package ZyRoLa_GUI;

import java.io.*;

import ZyRoLa_GUI.ZyrolaGui;

/* Waelzkörperanzahl wird noch direkt übert GUI eingelesen
 * 
 * 
 * 
 */

public class Spckbuilder {

	public File f;
	public PrintWriter pw = null;
	public FileWriter fw;
	public BufferedWriter bw;
	public String towrite;
	public int i_case = ZyrolaGui.ltypindex;    //  Index für case
	public String[] var = { "$G_GeometrieParameter.$_ZyRoLA_AR_LFB_B", "$G_GeometrieParameter.$_ZyRoLA_AR_Bord_B" , "$G_GeometrieParameter.$_ZyRoLA_AR_LFB_D", 
							"$G_GeometrieParameter.$_ZyRoLA_AR_Bord_D", "$G_GeometrieParameter.$_ZyRoLA_IR_LFB_B","$G_GeometrieParameter.$_ZyRoLA_IR_Bord_B" ,
							"$G_GeometrieParameter.$_ZyRoLA_IR_LFB_D", "$G_GeometrieParameter.$_ZyRoLA_IR_Bord_D","$G_GeometrieMaterialdaten.$_ZyRoLA_AR_rho",
							"$G_GeometrieMaterialdaten.$_ZyRoLA_IR_rho"};
	
	


	public Spckbuilder(File f1) {
		 try {
			
			 f = f1;
			
			fw = new FileWriter( f );
			bw = new BufferedWriter( fw );
			pw = new PrintWriter( bw );
			
			
		} catch ( IOException e ) {
			 
			 System.err.println( "Error creating file!" );
		}
	}
	
	
	
	public void saveit() { //schreibe alles was geschreiben werden soll in towrite

		towrite = "!file.version=1.7! Removing this line will make the file unreadable \r\n" +
				"\r\n" +
				"!Dateiname: "+f.getName().toString()+"  \r\n"+
				"!Anzahl Wälzkörper: "+ ZyrolaGui.wk_N +"  \r\n"+				
				"!**********************************************************************\r\n" + 
				"! SIMPACK Version Information\r\n" + 
				"!**********************************************************************\r\n" + 
				"version.number = 90600\r\n" + 
				"version.desc   = ''\r\n" + 
				"version.build  = 93\r\n" +
				"\r\n"+
				"\r\n"+ 
				"!**********************************************************************\r\n" + 
				"!  Global parameters\r\n" + 
				"!**********************************************************************\r\n" + 
				"searchpath (              1                                       ) = '.' \r\n" +
				"gravity (                 1                                       ) = 0.0000000000000000E+00  ! Gravity\r\n" + 
				"gravity (                 2                                       ) = {-9.81}  ! Gravity\r\n" + 
				"gravity (                 3                                       ) = 0.00! Gravity\r\n" + 
				"vehicle.startvel                                                    = 0.0000000000000000E+00  ! Vehicle initial velocity\r\n" + 
				"vehicle.applystartvel                                               = 0                       ! Apply vehicle initial velocity at solver start\r\n" + 
				"glob.compat.flxnodeori                                              = 0                       ! Flexbody Node Orientation Method\r\n" + 
				"glob.compat.afcf                                                    = 0                       ! References Frame for Joint and Force Element Forces and Torques\r\n" + 
				"glob.form.tuning                                                    = 0                       ! Formalism Tuning\r\n" + 
				"glob.ifctn.warn                                                     = 0                       ! Spline Warnings\r\n" + 
				"rw.cont.meth                                                        = 2                       ! MISCPAR_RW_CONTACT_METHOD\r\n" + 
				"rw.linear                                                           = 0                       ! MISCPAR_RW_PROFILE_LINEARIZATION_METHOD\r\n" + 
				"time                                                                = 0.0000000000000000E+00  ! Time\r\n" + 
				"slv.active                                                          = $SLV_SolverSettings     ! Active SolverSettings element\r\n" + 
				"track.active                                                        = 0                       ! Active Track element\r\n" +
				"prop3d.bg.base.color.r                                              = 2.5500000000000000E+02  ! Background color (red component)\r\n" + 
				"prop3d.bg.base.color.g                                              = 2.5500000000000000E+02  ! Background color (green component)\r\n" + 
				"prop3d.bg.base.color.b                                              = 2.5500000000000000E+02  ! Background color (blue component)\r\n" + 
				"prop3d.bg.base.color.t                                              = 0.0000000000000000E+00  ! Background color (transparency component)\r\n" + 
				"prop3d.bg.grad.color.r                                              = 2.5500000000000000E+02  ! Gradient color (red component)\r\n" + 
				"prop3d.bg.grad.color.g                                              = 2.5500000000000000E+02  ! Gradient color (green component)\r\n" + 
				"prop3d.bg.grad.color.b                                              = 2.5500000000000000E+02  ! Gradient color (blue component)\r\n" + 
				"prop3d.bg.grad.color.t                                              = 0.0000000000000000E+00  ! Gradient color (transparency component)\r\n" + 
				"prop3d.bg.gradient                                                  = 1                       ! 0 = constant, 1 = gradient\r\n" + 
				"prop3d.marker.size                                                  = 0.0000000000000000E+00  ! length of Marker axes, if value is zero, use \"auto\"\r\n" + 
				"prop3d.marker.auto                                                  = 1                       ! 0 = user defined, 1 = auto size determination\r\n" + 
				"prop3d.marker.color.r                                               = 0.0000000000000000E+00  ! Marker color (red component)\r\n" + 
				"prop3d.marker.color.g                                               = 0.0000000000000000E+00  ! Marker color (green component)\r\n" + 
				"prop3d.marker.color.b                                               = 0.0000000000000000E+00  ! Marker color (blue component)\r\n" + 
				"prop3d.marker.color.t                                               = 0.0000000000000000E+00  ! Marker color (transparency component)\r\n" + 
				"prop3d.display                                                      = 1                       ! 1 = shaded,  2 = wireframe, 3 = hidden line\r\n" + 
				"prop3d.bbox                                                         = 0                       ! 0 = exclude all refsys objects, 1 = include all refsys objects\r\n" + 
				"prop2d.joint.visible                                                = 1                       ! Joints visible\r\n" + 
				"prop2d.force.visible                                                = 1                       ! Forces visible\r\n" + 
				"prop2d.constr.visible                                               = 1                       ! Constraints visible\r\n" + 
				"prop2d.sensor.visible                                               = 0                       ! Sensors visible\r\n" + 
				"prop2d.grid.visible                                                 = 0                       ! Show grid\r\n" + 
				"prop2d.grid.hor.start                                               = 0.0000000000000000E+00  ! Horizontal offset\r\n" + 
				"prop2d.grid.hor.step                                                = 1.0000000000000000E+01  ! Horizontal step\r\n" + 
				"prop2d.grid.vert.start                                              = 0.0000000000000000E+00  ! Vertical offset\r\n" + 
				"prop2d.grid.vert.step                                               = 1.0000000000000000E+01  ! Vertical step\r\n" + 
				"prop2d.line.width                                                   = 0                       ! Line width\r\n" + 
				"prop2d.proj.dir                                                     = 2                       ! Projection direction\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"!**********************************************************************\r\n" + 
				"! Views\r\n" + 
				"!**********************************************************************\r\n" + 
				"view.type (                         $V_WorkingView                ) = 1                       ! Projection type\r\n" + 
				"view.pos (                1 ,       $V_WorkingView                ) = 6.0997214168310165E-02  ! Position\r\n" + 
				"view.pos (                2 ,       $V_WorkingView                ) = 3.9078075438737869E-02  ! Position\r\n" + 
				"view.pos (                3 ,       $V_WorkingView                ) = 1.7724007368087769E-01  ! Position\r\n" + 
				"view.orient (             1 ,       $V_WorkingView                ) = -1.0286927968263626E-01 ! Orientation\r\n" + 
				"view.orient (             2 ,       $V_WorkingView                ) = 1.6420675814151764E-01  ! Orientation\r\n" + 
				"view.orient (             3 ,       $V_WorkingView                ) = 1.8640009686350822E-02  ! Orientation\r\n" + 
				"view.orient (             4 ,       $V_WorkingView                ) = 9.8087036609649658E-01  ! Orientation\r\n" + 
				"view.angle (                        $V_WorkingView                ) = 2.5568020353152915E-01  ! Lens angle\r\n" + 
				"view.fclip.auto (                   $V_WorkingView                ) = 1                       ! Front clipping plane auto\r\n" + 
				"view.bclip.auto (                   $V_WorkingView                ) = 1                       ! Back clipping plane auto\r\n" + 
				"view.fclip.value (                  $V_WorkingView                ) = 9.9999997764825821E-03  ! Front clipping plane value\r\n" + 
				"view.bclip.value (                  $V_WorkingView                ) = 3.5884794592857361E-01  ! Back clipping plane value\r\n" + 
				"view.rotcenter.type (               $V_WorkingView                ) = 0                       ! Rotation center type\r\n" + 
				"view.rotcenter.adjust (             $V_WorkingView                ) = 1                       ! Adjust rotation center\r\n" + 
				"view.motion.active (                $V_WorkingView                ) = 0                       ! Camera is moved\r\n" + 
				"\r\n" + 
				"view.type (                         $V_Ortho                      ) = 0                       ! Projection type\r\n" + 
				"view.pos (                1 ,       $V_Ortho                      ) = 6.8524776119738817E-01  ! Position\r\n" + 
				"view.pos (                2 ,       $V_Ortho                      ) = -7.1946778753772378E-01 ! Position\r\n" + 
				"view.pos (                3 ,       $V_Ortho                      ) = 3.5240458619955461E-01  ! Position\r\n" + 
				"view.orient (             1 ,       $V_Ortho                      ) = 5.2440571784973145E-01  ! Orientation\r\n" + 
				"view.orient (             2 ,       $V_Ortho                      ) = 2.1178941428661346E-01  ! Orientation\r\n" + 
				"view.orient (             3 ,       $V_Ortho                      ) = 2.8827568888664246E-01  ! Orientation\r\n" + 
				"view.orient (             4 ,       $V_Ortho                      ) = 7.7268427610397339E-01  ! Orientation\r\n" + 
				"view.angle (                        $V_Ortho                      ) = 7.8539818525314331E-01  ! Lens angle\r\n" + 
				"view.fclip.auto (                   $V_Ortho                      ) = 1                       ! Front clipping plane auto\r\n" + 
				"view.bclip.auto (                   $V_Ortho                      ) = 1                       ! Back clipping plane auto\r\n" + 
				"view.rotcenter.type (               $V_Ortho                      ) = 0                       ! Rotation center type\r\n" + 
				"view.rotcenter.adjust (             $V_Ortho                      ) = 1                       ! Adjust rotation center\r\n" + 
				"view.motion.active (                $V_Ortho                      ) = 0                       ! Camera is moved\r\n" + 
				"\r\n" + 
				"view.type (                         $V_Front                      ) = 1                       ! Projection type\r\n" + 
				"view.pos (                1 ,       $V_Front                      ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"view.pos (                2 ,       $V_Front                      ) = -5.9984529018402100E-01 ! Position\r\n" + 
				"view.pos (                3 ,       $V_Front                      ) = 2.9802329493122670E-08  ! Position\r\n" + 
				"view.orient (             1 ,       $V_Front                      ) = 7.0710676908493042E-01  ! Orientation\r\n" + 
				"view.orient (             2 ,       $V_Front                      ) = 0.0000000000000000E+00  ! Orientation\r\n" + 
				"view.orient (             3 ,       $V_Front                      ) = 0.0000000000000000E+00  ! Orientation\r\n" + 
				"view.orient (             4 ,       $V_Front                      ) = 7.0710676908493042E-01  ! Orientation\r\n" + 
				"view.height (                       $V_Front                      ) = 8.0000000000000004E-01  ! View height\r\n" + 
				"view.fclip.auto (                   $V_Front                      ) = 1                       ! Front clipping plane auto\r\n" + 
				"view.bclip.auto (                   $V_Front                      ) = 1                       ! Back clipping plane auto\r\n" + 
				"view.rotcenter.type (               $V_Front                      ) = 0                       ! Rotation center type\r\n" + 
				"view.rotcenter.adjust (             $V_Front                      ) = 1                       ! Adjust rotation center\r\n" + 
				"view.motion.active (                $V_Front                      ) = 0                       ! Camera is moved\r\n" + 
				"\r\n" + 
				"view.type (                         $V_Top                        ) = 1                       ! Projection type\r\n" + 
				"view.pos (                1 ,       $V_Top                        ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"view.pos (                2 ,       $V_Top                        ) = -7.4989371933043003E-04 ! Position\r\n" + 
				"view.pos (                3 ,       $V_Top                        ) = 5.9909558296203613E-01  ! Position\r\n" + 
				"view.orient (             1 ,       $V_Top                        ) = 2.5144752058281483E-08  ! Orientation\r\n" + 
				"view.orient (             2 ,       $V_Top                        ) = 0.0000000000000000E+00  ! Orientation\r\n" + 
				"view.orient (             3 ,       $V_Top                        ) = 0.0000000000000000E+00  ! Orientation\r\n" + 
				"view.orient (             4 ,       $V_Top                        ) = 1.0000000000000000E+00  ! Orientation\r\n" + 
				"view.height (                       $V_Top                        ) = 8.0000000000000004E-01  ! View height\r\n" + 
				"view.fclip.auto (                   $V_Top                        ) = 1                       ! Front clipping plane auto\r\n" + 
				"view.bclip.auto (                   $V_Top                        ) = 1                       ! Back clipping plane auto\r\n" + 
				"view.rotcenter.type (               $V_Top                        ) = 0                       ! Rotation center type\r\n" + 
				"view.rotcenter.adjust (             $V_Top                        ) = 1                       ! Adjust rotation center\r\n" + 
				"view.motion.active (                $V_Top                        ) = 0                       ! Camera is moved\r\n" + 
				"\r\n" +
				"view.type (                         $V_Start                      ) = 1                       ! Projection type\r\n" + 
				"view.pos (                1 ,       $V_Start                      ) = 6.0997214168310165E-02  ! Position\r\n" + 
				"view.pos (                2 ,       $V_Start                      ) = 3.9078075438737869E-02  ! Position\r\n" + 
				"view.pos (                3 ,       $V_Start                      ) = 1.7724007368087769E-01  ! Position\r\n" + 
				"view.orient (             1 ,       $V_Start                      ) = -1.0286927968263626E-01 ! Orientation\r\n" + 
				"view.orient (             2 ,       $V_Start                      ) = 1.6420675814151764E-01  ! Orientation\r\n" + 
				"view.orient (             3 ,       $V_Start                      ) = 1.8640009686350822E-02  ! Orientation\r\n" + 
				"view.orient (             4 ,       $V_Start                      ) = 9.8087036609649658E-01  ! Orientation\r\n" + 
				"view.height (                       $V_Start                      ) = 2.5568020353152915E-01  ! View height\r\n" + 
				"view.fclip.auto (                   $V_Start                      ) = 1                       ! Front clipping plane auto\r\n" + 
				"view.bclip.auto (                   $V_Start                      ) = 1                       ! Back clipping plane auto\r\n" + 
				"view.fclip.value (                  $V_Start                      ) = 9.9999997764825821E-03  ! Front clipping plane value\r\n" + 
				"view.bclip.value (                  $V_Start                      ) = 3.5884794592857361E-01  ! Back clipping plane value\r\n" + 
				"view.rotcenter.type (               $V_Start                      ) = 0                       ! Rotation center type\r\n" + 
				"view.rotcenter.adjust (             $V_Start                      ) = 1                       ! Adjust rotation center\r\n" + 
				"view.motion.active (                $V_Start                      ) = 0                       ! Camera is moved"+
				"\r\n"+
				"!**********************************************************************\r\n" + 
				"! Solver Settings\r\n" + 
				"!**********************************************************************\r\n" + 
				"slv.kin.task (                      $SLV_SolverSettings           ) = 3                       ! KIN_INTPAR_TASK\r\n" + 
				"slv.kin.tout.n (                    $SLV_SolverSettings           ) = 201                     ! KIN_INTPAR_NUM_STEPS\r\n" + 
				"slv.kin.tol (                       $SLV_SolverSettings           ) = 9.9999999999999995E-07  ! Kinematics tolerance\r\n" + 
				"slv.integ.maxrhs (                  $SLV_SolverSettings           ) = -1                      ! TIME_INTEG_INTPAR_MAX_RHS\r\n" + 
				"slv.integ.sodasrt.maxord (          $SLV_SolverSettings           ) = 5                       ! TIME_INTEG_INTPAR_SODASRT_MAX_ORDER\r\n" + 
				"slv.integ.lsode.maxord (            $SLV_SolverSettings           ) = 5                       ! TIME_INTEG_INTPAR_LSODE_MAX_ORDER\r\n" + 
				"slv.integ.newton.maxit (            $SLV_SolverSettings           ) = 7                       ! TIME_INTEG_INTPAR_NEWTON_ITERS\r\n" + 
				"slv.integ.maxstp (                  $SLV_SolverSettings           ) = -1                      ! TIME_INTEG_INTPAR_MAX_TIME_STEPS\r\n" + 
				"slv.integ.fix.driftstab (           $SLV_SolverSettings           ) = 1                       ! TIME_INTEG_INTPAR_FIXINT_CONSTAB\r\n" + 
				"slv.integ.fix.part.type (           $SLV_SolverSettings           ) = 1                       ! TIME_INTEG_INTPAR_FIXINT_JAC_PART_TYPE\r\n" + 
				"slv.integ.tout.mode (               $SLV_SolverSettings           ) = 0                       ! TIME_INTEG_INTPAR_TOUT_MODE\r\n" +
				"slv.integ.tout.n (                  $SLV_SolverSettings           ) =  0   ! TIME_INTEG_INTPAR_OUTPUT_NUM_POINTS\r\n" +
				"slv.integ.linalg.struc (            $SLV_SolverSettings           ) = 2                       ! TIME_INTEG_INTPAR_SODASRT_LINALG_ROUTINES\r\n" + 
				"slv.integ.meetop (                  $SLV_SolverSettings           ) = 0                       ! TIME_INTEG_BOOLPAR_EVAL_MOTION_EQ_AT_COMM_POINTS\r\n" + 
				"slv.integ.driftstab (               $SLV_SolverSettings           ) = 2                       ! TIME_INTEG_BOOLPAR_ACCEPT_DRIFT\r\n" + 
				"slv.integ.root (                    $SLV_SolverSettings           ) = 1                       ! TIME_INTEG_BOOLPAR_DETECT_IMPLICIT_DISCONTINUITIES\r\n" + 
				"slv.integ.jac.part (                $SLV_SolverSettings           ) = 1                       ! TIME_INTEG_BOOLPAR_PARTITIONED_EVAL_JACOBIAN\r\n" + 
				"slv.integ.addrhscall (              $SLV_SolverSettings           ) = 0                       ! TIME_INTEG_BOOLPAR_EXTRA_EVAL_AFTER_SUCCESSFUL_STEP\r\n" + 
				"slv.integ.fix.oneleg (              $SLV_SolverSettings           ) = 1                       ! TIME_INTEG_BOOLPAR_FIXINT_IMPLICIT_ONE_LEG_OLC\r\n" + 
				"slv.integ.fix.conforce (            $SLV_SolverSettings           ) = 0                       ! TIME_INTEG_BOOLPAR_FIXINT_CONFORCE\r\n" + 
				"slv.integ.fix.class (               $SLV_SolverSettings           ) = 1                       ! TIME_INTEG_BOOLPAR_FIXINT_METHOD_CLASS\r\n" + 
				"slv.integ.sodasrt1.scal.lam (       $SLV_SolverSettings           ) = 9.9999999999999995E-08  ! TIME_INTEG_SCALARPAR_SCALE_CONSTRAINT_FORCES\r\n" + 
				"slv.integ.sodasrt1.scal.acc (       $SLV_SolverSettings           ) = 1.0000000000000000E+00  ! TIME_INTEG_SCALARPAR_SCALE_ACCELERATIONS\r\n" + 
				"slv.integ.newton.eps (              $SLV_SolverSettings           ) = 3.3300000000000002E-01  ! TIME_INTEG_SCALARPAR_NEWTON_ERROR_BOUND\r\n" + 
				"slv.integ.fix.olcpar (              $SLV_SolverSettings           ) = 5.0000000000000000E-01  ! TIME_INTEG_SCALARPAR_FIXINT_ONE_LEG_OLC\r\n" + 
				"slv.integ.sodasrt2.estfac (         $SLV_SolverSettings           ) = 9.9999999999999995E-07  ! TIME_INTEG_SCALARPAR_SODASRT20_ERROR_EST_SCALE_FACTOR\r\n";				
	if (ZyrolaGui.s_lagermod == 0) towrite = towrite + "slv.integ.tend.time (               $SLV_SolverSettings           ) = {$G_Simulationsparameter.$_ZyRoLA_simt}		   ! TIME_INTEG_PAR_END_TIME\r\n";
	if (ZyrolaGui.s_lagermod == 1) towrite = towrite + "slv.integ.tend.time (               $SLV_SolverSettings           ) =  0		   ! TIME_INTEG_PAR_END_TIME\r\n"; 

	if (ZyrolaGui.s_lagermod == 0) towrite = towrite + "slv.integ.tout.freq (                  $SLV_SolverSettings           ) =  {$G_Simulationsparameter.$_ZyRoLA_simfreq}   ! TIME_INTEG_PAR_OUTPUT_FREQ\r\n";
	if (ZyrolaGui.s_lagermod == 1) towrite = towrite + "slv.integ.tout.freq (                  $SLV_SolverSettings           ) =  0   ! TIME_INTEG_PAR_OUTPUT_FREQ\r\n";
				towrite = towrite +
				"slv.integ.hmax (                    $SLV_SolverSettings           ) = 1.0000000000000001E-05  ! TIME_INTEG_TIMEPAR_MAX_STEPSIZE\r\n" + 
				"slv.integ.atol.gen (                $SLV_SolverSettings           ) = 1.0000000000000001E-05  ! TIME_INTEG_PARSTATE_DEFAULT\r\n" + 
				"slv.integ.atol.vel (                $SLV_SolverSettings           ) = 1.0000000000000000E-04  ! TIME_INTEG_PARSTATE_VEL\r\n" + 
				"slv.integ.rtol.gen (                $SLV_SolverSettings           ) = 9.9999999999999995E-08  ! TIME_INTEG_PARSTATE_DEFAULT\r\n" + 
				"slv.integ.rtol.vel (                $SLV_SolverSettings           ) = 1.0000000000000001E-05  ! TIME_INTEG_PARSTATE_VEL\r\n" + 
				"slv.integ.type (                    $SLV_SolverSettings           ) = 1                       ! TIME_INTEG_PAR_INTEG_METHOD\r\n" + 
				"slv.integ.lsode.meth (              $SLV_SolverSettings           ) = 1                       ! TIME_INTEG_PAR_LSODE_INTEG_METHOD\r\n" + 
				"slv.integ.sodasrt.formalism (       $SLV_SolverSettings           ) = 1                       ! TIME_INTEG_PAR_FORMALISM\r\n" + 
				"slv.integ.jac.struc (               $SLV_SolverSettings           ) = 3                       ! SOLVER_TIME_INTEG_PAR_JACOBIAN_STRUC\r\n" + 
				"slv.integ.lsode.jac.struc (         $SLV_SolverSettings           ) = 3                       ! SOLVER_TIME_INTEG_PAR_LSODE_JACOBIAN_STRUC\r\n" + 
				"slv.integ.sodasrt1.errnorm (        $SLV_SolverSettings           ) = 3                       ! TIME_INTEG_PAR_SODASRT_NORM_TYPE\r\n" + 
				"slv.integ.jac.incr.type (           $SLV_SolverSettings           ) = 2                       ! TIME_INTEG_PAR_SODASRT_INCR_TYPE_DIFF_APPROX\r\n" + 
				"slv.integ.sodasrt2.iestsc (         $SLV_SolverSettings           ) = 2                       ! TIME_INTEG_PAR_SODASRT20_ERROR_EST_SCALE_METHOD\r\n" + 
				"slv.integ.fix.order (               $SLV_SolverSettings           ) = 1                       ! TIME_INTEG_PAR_FIXINT_METHOD_ORDER\r\n" + 
				"slv.integ.fix.posup (               $SLV_SolverSettings           ) = 1                       ! TIME_INTEG_PAR_FIXINT_POS_COORD_UPDATE\r\n" + 
				"slv.integ.fix.jac.update (          $SLV_SolverSettings           ) = 3                       ! TIME_INTEG_PAR_FIXINT_JACOBIAN_EVAL\r\n" + 
				"slv.meas.geo (                      $SLV_SolverSettings           ) = 1                       ! Animation data to result file\r\n" + 
				"slv.meas.sensor.pos (               $SLV_SolverSettings           ) = 1                       ! Sensor position to result file\r\n" + 
				"slv.meas.sensor.vel (               $SLV_SolverSettings           ) = 1                       ! Sensor velocity to result file\r\n" + 
				"slv.meas.sensor.acc (               $SLV_SolverSettings           ) = 1                       ! Sensor acceleration to result file\r\n" + 
				"slv.meas.force.af (                 $SLV_SolverSettings           ) = 1                       ! applied forces to result file\r\n" + 
				"slv.meas.force.ov (                 $SLV_SolverSettings           ) = 1                       ! Force outpur values to result file\r\n" + 
				"slv.meas.force.st.dyn (             $SLV_SolverSettings           ) = 1                       ! Force dynamic states to result file\r\n" + 
				"slv.meas.force.st.alg (             $SLV_SolverSettings           ) = 1                       ! Force algebraic states to result file\r\n" + 
				"slv.meas.joint.st.pos (             $SLV_SolverSettings           ) = 1                       ! Joint position to result file\r\n" + 
				"slv.meas.joint.st.vel (             $SLV_SolverSettings           ) = 1                       ! Joint velocity to result file\r\n" + 
				"slv.meas.joint.st.acc (             $SLV_SolverSettings           ) = 1                       ! Joint acceleration to result file\r\n" + 
				"slv.meas.joint.cf (                 $SLV_SolverSettings           ) = 1                       ! Joint constraining forces to result file\r\n" + 
				"slv.meas.body.pos (                 $SLV_SolverSettings           ) = 1                       ! Body position to result file\r\n" + 
				"slv.meas.body.vel (                 $SLV_SolverSettings           ) = 1                       ! Body velocity to result file\r\n" + 
				"slv.meas.body.acc (                 $SLV_SolverSettings           ) = 1                       ! Body acceleration to result file\r\n" + 
				"slv.meas.body.flx.st.pos (          $SLV_SolverSettings           ) = 1                       ! Body elastic state position to result file\r\n" + 
				"slv.meas.body.flx.st.vel (          $SLV_SolverSettings           ) = 1                       ! Body elastic state velocity to result file\r\n" + 
				"slv.meas.body.flx.st.acc (          $SLV_SolverSettings           ) = 1                       ! Body elastic state acceleration to result file\r\n" + 
				"slv.meas.marker.st.alg (            $SLV_SolverSettings           ) = 1                       ! Marker algebraic state to result file\r\n" + 
				"slv.meas.constr.cf (                $SLV_SolverSettings           ) = 1                       ! Constraint constrained state to result file\r\n" + 
				"slv.meas.yout (                     $SLV_SolverSettings           ) = 1                       ! y-Outputs to result file\r\n" + 
				"slv.meas.result (                   $SLV_SolverSettings           ) = 1                       ! Result elements to result file\r\n" + 
				"slv.meas.subvar (                   $SLV_SolverSettings           ) = 1                       ! MEAS_BOOLPAR_SUBVAR\r\n" + 
				"slv.assmbl.task (                   $SLV_SolverSettings           ) = 3                       ! ASSMBL_PAR_SOLVER_MODE\r\n" + 
				"slv.assmbl.tol (                    $SLV_SolverSettings           ) = 1.0000000000000000E-08  ! ASSMBL_PAR_SOLVER_ERROR_TOLERANCE\r\n" + 
				"slv.output.path.type (              $SLV_SolverSettings           ) = 1                       ! OUTPUT_PATH_PAR_TYPE\r\n" + 
				"slv.output.file.basename (          $SLV_SolverSettings           ) = 'result.output'         ! OUTPUT_FILEBASENAME\r\n" + 
				"slv.txt (                           $SLV_SolverSettings           ) = ''                      ! Comment" +
				"\r\n" + 
				
				"refsys.type (                       $R_Isys                       ) = 1                       ! Type\r\n" + 
				"refsys.attr.2d.pos.x (              $R_Isys                       ) = -9.0000000000000000E+01\r\n" + 
				"refsys.attr.2d.pos.y (              $R_Isys                       ) = -1.0000000000000000E+01\r\n" + 
				"refsys.attr.2d.width (              $R_Isys                       ) = 2.4000000000000000E+02\r\n" + 
				"refsys.attr.2d.height (             $R_Isys                       ) = 2.2000000000000000E+02\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Isys                       ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Isys                       ) = $R_Isys                 ! Reference system\r\n" + 
				"\r\n" + 
				"prim.type (                         $P_Isys                       ) = 30                      ! Type\r\n" + 
				"prim.ref (                          $P_Isys                       ) = $M_Isys                 ! Reference Marker\r\n" + 
				"prim.color.r (            1 ,       $P_Isys                       ) = 1.4800000000000000E+02  ! Colors (red component)\r\n" + 
				"prim.color.g (            1 ,       $P_Isys                       ) = 1.4800000000000000E+02  ! Colors (green component)\r\n" + 
				"prim.color.b (            1 ,       $P_Isys                       ) = 1.4800000000000000E+02  ! Colors (blue component)\r\n" + 
				"prim.color.t (            1 ,       $P_Isys                       ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.par (                3 ,       $P_Isys                       ) = 1.0000000000000001E-01  ! [m] Length\r\n" + 
				"\r\n";
		
		
	//Subvars

		
	towrite = towrite +
			"!**********************************************************************\r\n" + 
			"! SubVars\r\n" + 
			"!**********************************************************************\r\n" +
			"\r\n" +
			"\r\n";
	if (ZyrolaGui.s_lagermod == 0) { //Einzellager 2*3.14159*$_"+dateinameklein+"_dREH_U)/60
	towrite = towrite +
			"subvargroup.begin (                 $G_Simulationsparameter             )                           ! $G_Simulationsparameter\r\n"+
			"\tsubvar.str (                        $_ZyRoLA_dREH_U       		  )  =  '"+(ZyrolaGui.s_EL_drehzahl/60*2*Math.PI)+"'\r\n"+
			"\tsubvar.str (                        $_ZyRoLA_FA           		  )  =  '"+ZyrolaGui.s_EL_F_ax+"'\r\n"+
			"\tsubvar.str (                        $_ZyRoLA_FR           		  )  =  '"+ZyrolaGui.s_EL_F_rad+"'\r\n"+
			"\tsubvar.str (                        $_ZyRoLA_simt            	  )  =  '"+ZyrolaGui.s_EL_simt+"'\r\n"+
			"\tsubvar.str (                        $_ZyRoLA_simfreq         	  )  =  '"+ZyrolaGui.s_EL_simfreq+"'\r\n"+
			"\tsubvar.str (                        $_ZyRoLA_simrampent      	  )  =  '"+ZyrolaGui.s_EL_simrampent+"'\r\n"+
			"subvargroup.end (                   $G_Simulationsparameter              )                           ! $G_Simulationsparameter\r\n"+
			"\r\n" +
			"\r\n" ;
	
	} else if (ZyrolaGui.s_lagermod == 1) {//Systemlager
		
		double nI = ZyrolaGui.s_SL_IR_omega;
		double nA = ZyrolaGui.s_SL_AR_omega;
		double Dw = ZyrolaGui.wk_D;
		double Dpw = (ZyrolaGui.ar_LFB_D+ZyrolaGui.ir_LFB_D)/2;
		double nKF = (1-Dw/Dpw)*nI/2 + (1+Dw/Dpw)*nA/2 ;
		double nWK = (Dpw/Dw-Dw/Dpw)*(nA-nI)/2;
		
 
		
		
		
	towrite = towrite +			
		"subvargroup.begin (                 $G_Simulationsparameter             )                           ! $G_Simulationsparameter\r\n"+
		"\tsubvar.str (                        $_ZyRoLA_dREH_U       		  )  =  '0'\r\n"+
		"\tsubvar.str (                        $_ZyRoLA_FA           		  )  =  '0'\r\n"+
		"\tsubvar.str (                        $_ZyRoLA_FR           		  )  =  '0'\r\n"+
		"\tsubvar.str (                        $_ZyRoLa_sim_dreh_KF           )  =  '"+(nKF/60*2*Math.PI)+"'\r\n"+
		"\tsubvar.str (                        $_ZyRoLa_sim_dreh_WK           )  =  '"+(nWK/60*2*Math.PI)+"'\r\n"+
		"\tsubvar.str (                        $_ZyRoLa_sim_dreh_AR      	  )  =  '"+ZyrolaGui.s_SL_AR_omega+"'\r\n"+
		"\tsubvar.str (                        $_ZyRoLa_sim_dreh_IR      	  )  =  '"+ZyrolaGui.s_SL_IR_omega+"'\r\n"+
		"subvargroup.end (                   $G_Simulationsparameter              )                           ! $G_Simulationsparameter\r\n"+
		"\r\n" +
		"\r\n" ;
	 					
	
	}
	
	towrite = towrite +
			"subvargroup.begin (                 $G_sonstigeParameter             )                           ! $G_sonstigeParameter\r\n"+
			"\tsubvar.str (                        $_ZyRoLA_alpha					) = '90deg'                 ! Definition"+
			"\r\n" + 
			"\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_EHD					)  =  '1'\r\n" +
			"! Vektor mit Parametern für Scheibenmodell, Gauss-Integration, Curvefit\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_ctctype_LB			)  = '" +ZyrolaGui.s_ctctype_LB+ " '        \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_no_gauss_LB			)  = '" +ZyrolaGui.s_no_gauss_LB+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_cf_pnts_LB			)  = '" +ZyrolaGui.s_cf_pnts_LB+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_cf_p_max_LB			)  = '" +ZyrolaGui.s_cf_p_max_LB+ " '\r\n" +
			"\r\n" +
			"! Vektor mit GForce-IDs für Ausgabe von Ergebnissen in PostProcessor/Datei\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_gfo_id_AR				)  =  1.0000000000000000E+00\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_gfo_id_IR				)  =  2.0000000000000000E+00\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_flex_ar					)  =  '"+ZyrolaGui.flex_ar+"'\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_FE_NForce_flex			)  =  "+ZyrolaGui.flexAR_maxFE+"\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_ASCII					)  =  '"+ZyrolaGui.ascii_ausgabe+"'  \r\n" 	+	
			"subvargroup.end (                   $G_sonstigeParameter              )                           ! $G_sonstigeParameter\r\n"+
			"\r\n" +
			"\r\n" +
			"! Geometrie Parameter\r\n" + 
			"subvargroup.begin (                 $G_GeometrieParameter         	    )                           ! $G_GeometrieParameter\r\n"+
			"\tsubvar.str (                        $_ZyRoLA_WK_D					)  =  '" +ZyrolaGui.wk_D/1000+ "'\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_WK_z					)  =  '" +ZyrolaGui.wk_z/1000+ "'\r\n" + 
			"\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_AR_PROF_D				)  =  '2000'\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_AR_A_D					)  =  '" +ZyrolaGui.ar_A_D/1000+ "'\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_AR_LFB_D				)  =  '" +ZyrolaGui.ar_LFB_D/1000+ "'\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_AR_LFB_B				)  =  '" +ZyrolaGui.ar_LFB_B/1000+ "'\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_AR_Bord_D				)  =  '" +ZyrolaGui.ar_BORD_D/1000+ "'\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_AR_Bord_B				)  =  '" +ZyrolaGui.ar_BORD_B/1000+ "'\r\n" + 
			"\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_IR_PROF_D				)  =  '2000 '\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_IR_I_D					)  =  '" +ZyrolaGui.ir_I_D/1000+ "'\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_IR_LFB_D				)  =  '" +ZyrolaGui.ir_LFB_D/1000+ "'\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_IR_LFB_B				)  =  '" +ZyrolaGui.ir_LFB_B/1000+ "'\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_IR_Bord_D				)  =  '" +ZyrolaGui.ir_BORD_D/1000+ "'\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_IR_Bord_B				)  =  '" +ZyrolaGui.ir_BORD_B/1000+ "'\r\n"+			
			"\r\n" +
			"!  Geometrievektor 1 (Außen- und Innenring)\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_prorad_AR				)  =  '2000 '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_prorad_IR				)  =  '2000 '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_AR_Dicke				)  =  '($G_GeometrieParameter.$_ZyRoLA_AR_A_D-$G_GeometrieParameter.$_ZyRoLA_AR_LFB_D)/2*1000 '\r\n" +
			"\r\n" +
			"!  Geometrievektor 2 (Waelzkörper)\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_l_WK					)  =  '2000 '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_prorad_WK				)  =  1400.0\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_protype					)  =  '"+ZyrolaGui.wk_protype+"'\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_WKpro_ap				)  =  0.00035 \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_WKpro_cp				)  =  '"+ZyrolaGui.wk_pro_cp+" '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_WKpro_dp				)  =  0.0     \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_WKpro_kp				)  =  '"+ZyrolaGui.wk_pro_kp+"' \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_WKpro_rk				)  =  '"+ZyrolaGui.wk_pro_rk+"' \r\n" +
			"subvargroup.end (                 $G_GeometrieParameter             )                           ! $G_GeometrieParameter\r\n"+
			"\r\n" +
			"!  Vektor mit Materialdaten\r\n" + 
			"subvargroup.begin (                 $G_GeometrieMaterialdaten            )                           ! $G_GeometrieMaterialdaten\r\n"+			
			"\tsubvar.str (                        $_ZyRoLA_IR_E					)  =  '" +ZyrolaGui.ir_E+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_IR_v					)  =  '" +ZyrolaGui.ir_v+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_AR_E					)  =  '" +ZyrolaGui.ar_E+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_AR_v					)  =  '" +ZyrolaGui.ar_v+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_WK_E					)  =  '" +ZyrolaGui.wk_E+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_WK_v					)  =  '" +ZyrolaGui.wk_v+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_AR_rho					) = '"+ZyrolaGui.ar_rho+"'  ! [-] Dichte Aussenring\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_IR_rho					) = '"+ZyrolaGui.ir_rho+"'  ! [-] Dichte Innenring\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_WK_rho					) = '"+ZyrolaGui.wk_rho+"'  ! [-] Dichte Wälzkörper\r\n" + 
			"subvargroup.end (                 $G_GeometrieMaterialdaten            )                           ! $G_GeometrieMaterialdaten\r\n"+
			"\r\n" +
			"!  Vektor mit Reibwert- und Schmierstoffdaten\r\n" + 
			"subvargroup.begin (                 $G_SchmierungReibungDaten      )                           ! $G_SchmierungReibungDaten\r\n"+
			"\tsubvar.str (                        $_ZyRoLA_race_f_mod			)  =  '2 '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_race_f_EHD			)  =  '" +ZyrolaGui.r_race_f_EHD+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_race_lub_mod		)  =  '" +ZyrolaGui.r_race_lub_mod+ " ' \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_tau_mod				)  =  '1 '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_filmT_mod			)  =  '1 '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_comprT_mod			)  =  '1 '\r\n" +
			"\r\n" +
			"\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_vel_s				)  =  '" +ZyrolaGui.r_vel_s+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_vel_d				)  =  '" +ZyrolaGui.r_vel_d+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_mu_s				)  =  '" +ZyrolaGui.r_mu_s+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_mu_d				)  =  '" +ZyrolaGui.r_mu_d+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_lubtmp				)  =  '" +ZyrolaGui.r_lubtmp+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_etaZero				)  =  '" +ZyrolaGui.r_etaZero+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_alphaT				)  =  '" +ZyrolaGui.r_alphaT+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_alphaP				)  =  '" +ZyrolaGui.r_alphaP+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_lambdaZero			)  =  '" +ZyrolaGui.r_lambdaZero+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_alphaLambda			)  =  '" +ZyrolaGui.r_alphaLambda + " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_C1					)  =  ' 1.387 '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_C2					)  =  ' 81.71899999999999 '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_AV					)  =  '" +ZyrolaGui.r_AV+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_Bv					)  =  '" +ZyrolaGui.r_Bv+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_CV					)  =  '" +ZyrolaGui.r_CV+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_DR					)  =  '" +ZyrolaGui.r_DR+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_ER					)  =  '" +ZyrolaGui.r_ER+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_pR					)  =  '" +ZyrolaGui.r_pR+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_B					)  =  '" +ZyrolaGui.r_B+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_C					)  =  '" +ZyrolaGui.r_C+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_K					)  =  '" +ZyrolaGui.r_K+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_a1					)  =  '" +ZyrolaGui.r_a1+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_a2					)  =  '" +ZyrolaGui.r_a2+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_b1					)  =  '" +ZyrolaGui.r_b1+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_b2					)  =  '" +ZyrolaGui.r_b2+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_rhoZero				)  =  '" +ZyrolaGui.r_rhoZero+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_tauRheo				)  =  ' "+ZyrolaGui.r_tauRheo+" '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_sigma				)  =  '" +ZyrolaGui.r_sigma+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_BZH					)  =  '" +ZyrolaGui.r_BZH+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_CZH					)  =  '" +ZyrolaGui.r_CZH+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_alphaHys			)  =  ' 0.08400000000000001 '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_scaleHys			)  =  ' 1.0 '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_cr					)  =  '" +ZyrolaGui.r_cr+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_rexp				)  =  '" +ZyrolaGui.r_rexp+ " '\r\n" +
			"subvargroup.end (                 $G_SchmierungReibungDaten        )                           ! $G_SchmierungReibungDaten\r\n"+
			"\r\n" +
			"! Vektor mit Daempfungsdaten\r\n" + 
			"subvargroup.begin (                 $G_Daempfungsdaten             )                           ! $Daempfungsdaten\r\n"+
			"\tsubvar.str (                        $_ZyRoLA_d_mod				)  =  1.0000000000000000E+00 \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_p_t					)  =  1.0000000000000000E-03 \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_d_max				)  =  3.0000000000000000E+01 \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_K0					)  =  1.9630000000000000E-01 \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_KR					)  =  7.8100000000000003E-01 \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_KL					)  =  7.6900000000000002E-01 \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_KE					)  =  1.0690000000000000E+00 \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_Keta				)  =  5.3100000000000003E-01 \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_Kalpha				)  =  4.2399999999999999E-01 \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_Kq					)  =  -1.3600000000000001E-01\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_Ku					)  =  -4.3400000000000000E-01\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_Kf					)  =  -5.6299999999999994E-01\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_fe					)  =  2.0000000000000000E+00 \r\n" +
			"subvargroup.end (                 $G_Daempfungsdaten             	)                           ! $Daempfungsdaten\r\n"+
			"\r\n" +
			"\r\n" +
			"\r\n" +
			"! Vektor mit Variablen für Bordkräfte\r\n" +
			"subvargroup.begin (                 $G_Bordkraefte            )                           ! $G_Bordkraefte\r\n"+
			"\tsubvar.str (                        $_ZyRoLA_vel_s_BRD			)  =  '" +ZyrolaGui.r_bord_vel_s+ " ' \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_vel_d_BRD			)  =  '" +ZyrolaGui.r_bord_vel_d+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_mu_s_BRD			)  =  '" +ZyrolaGui.r_bord_mu_s+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_mu_d_BRD			)  =  '" +ZyrolaGui.r_bord_mu_d+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_AR_LFB_BRD			)  =  '" +ZyrolaGui.ar_LFB_B_r+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_IR_LFB_BRD			)  =  '" +ZyrolaGui.ir_LFB_B_r+ " '\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_d_max_BRD			)  = 5.0000000000000000E+01  ! [-] d_max\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_p_t_BRD				)  = 1.0000000000000000E-03  ! [-] p_t\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_AR_oew				)  = '" +ZyrolaGui.ar_BORD_Oew+ " '  ! \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_IR_oew				)  = '" +ZyrolaGui.ir_BORD_Oew+ " '  ! \r\n" + 
			"subvargroup.end (                 $G_Bordkraefte            )                           ! $G_Bordkraefte\r\n"+
			"\r\n" +
			"! Vektor mit Variablen für Kräfte Taschenfederkäfig\r\n" +
			"subvargroup.begin (                 $G_Kraefte_Taschenfederkaefig           )                           ! $G_Kraefte_Taschenfederkaefig\r\n"+
			"\tsubvar.str (                        $_ZyRoLA_K_tspiel_tan		)  =  '"+ZyrolaGui.k_tspiel_tan+"' \r\n" +
			"\tsubvar.str (                        $_ZyRoLA_K_tspiel_rad		)  =  '"+ZyrolaGui.k_tspiel_rad+"'\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_K_tspiel_ax			)  =  '"+ZyrolaGui.k_tspiel_ax+"'\r\n" +
			"subvargroup.end (                 $G_Kraefte_Taschenfederkaefig           )                           ! $G_Kraefte_Taschenfederkaefig\r\n"+
			"\r\n" +
			"! Vektor mit Variablen für Kräfte Kontaktfäfig\r\n" +
			"subvargroup.begin (                 $G_Kraefte_Kontaktfaefig           )                           ! $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_rho\r\n"+
			"\tsubvar.str (                        $_ZyRoLA_K_vel_s_KF			)  =  '" +ZyrolaGui.r_kaefig_vel_s+ "'\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_K_vel_d_KF			)  =  '" +ZyrolaGui.r_kaefig_vel_d+ "'\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_K_mu_s_KF 			)  =  '" +ZyrolaGui.r_kaefig_mu_s+ "'\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_K_mu_d_KF			)  =  '" +ZyrolaGui.r_kaefig_mu_d+ "'\r\n" +
			"\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_K_rho				) = '"+ZyrolaGui.k_rho+"'  ! [-] Dichte Käfig\r\n" + 
			"\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_K_D_aussen				) = '"+ZyrolaGui.k_D_aussen/1000+"'  ! [-] Käfig\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_K_D_innen				) = '"+ZyrolaGui.k_D_innen/1000+"'  ! [-]  Käfig\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_K_Breite				) = '"+ZyrolaGui.k_Breite/1000+"'  ! [-]  Käfig\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_K_DickeSteg				) = '"+ZyrolaGui.k_DickeSteg/1000+"'  ! [-] Dicke des Steges\r\n" +
			"\r\n" +
			"\tsubvar.str (                        $_ZyRoLA_K_E					) = '"+ZyrolaGui.k_E+"'  ! [-] Elastizitätsmodul Käfigs\r\n" + 	
			"\tsubvar.str (                        $_ZyRoLA_K_v					) = '"+ZyrolaGui.k_v+"'  ! [-] Querkontraktionszahl Käfig\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_K_D_tasche			) = '"+ZyrolaGui.k_D_tasche/1000+"'  ! [-] Käfig Taschendurchmesser\r\n" +
			
			"\tsubvar.str (                        $_ZyRoLA_K_cf_pnts_KF		) = 1.0000000000000000E+02  ! [-] Anzahl der Curvefit-Punkte\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_K_cf_p_max_KF		) = 4.0000000000000000E+01  ! [-] P_Max für Curvefit\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_K_d_mod				) = 1.0000000000000000E+00  ! [-] d_mod\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_K_p_t				) = 1.0000000000000000E-02  ! [-] p_t\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_K_d_max				) = 1.0000000000000000E+01  ! [-] d_max\r\n" + 
			"\tsubvar.str (                        $_ZyRoLA_K_race_f_mod		) = 2.0000000000000000E+00  ! [-] Festkörperreibmodell\r\n" + 
			"\r\n" +
			"\tsubvar.str (                        $_KontaktModell_WK_KF         ) = '2'                     ! Definition\r\n"+
			"subvargroup.end (                 $G_Kraefte_Kontaktfaefig           )                           ! $G_Kraefte_Kontaktfaefig\r\n"+
			"\r\n" +
			"\r\n" +                                         
			"\r\n" +
			"\r\n" +
			"! Vektor mit Positionsdaten für WK\r\n"+
			"subvargroup.begin (                 $G_WK_PositionsDaten           )                           ! $G_WK_PositionsDaten\r\n";
	
	for (int i=1; i<= ZyrolaGui.wk_N; i++) {
		
		towrite = towrite +
				"\tsubvar.str (                        $_ZyRoLA_gamma" +i+ "         ) = '-(360/"+ZyrolaGui.wk_N+")*("+ (i-1) +"deg)'          ! Definition\r\n" + 
				"\r\n" + 
				"\tsubvar.str (                        $_ZyRoLA_WK" +i+ "_x          ) = '(($G_GeometrieParameter.$_ZyRoLA_AR_LFB_D+$G_GeometrieParameter.$_ZyRoLA_IR_LFB_D)/4)*sin(-$G_WK_PositionsDaten.$_ZyRoLA_gamma" +i+ ")' ! Definition\r\n" + 
				"\r\n" + 
				"\tsubvar.str (                        $_ZyRoLA_WK" +i+ "_y          ) = '(($G_GeometrieParameter.$_ZyRoLA_AR_LFB_D+$G_GeometrieParameter.$_ZyRoLA_IR_LFB_D)/4)*cos($G_WK_PositionsDaten.$_ZyRoLA_gamma" +i+ ")' ! Definition\r\n" + 
				"\r\n";	
	}
	towrite = towrite + "subvargroup.end (                 $G_WK_PositionsDaten           )                           ! $G_WK_PositionsDaten\r\n"+
			"\r\n" +
			"\r\n" +
			"\r\n" ;
	
	// Bodies werden geschreiben	
	towrite = towrite +	
				"!**********************************************************************\r\n" + 
				"! Bodies\r\n" + 
				"!**********************************************************************\r\n" +
				
				//Aussenring
				"body.m (                            $B_Aussenring                 ) = 1.0000000000000000E+00  ! Mass of the Body\r\n" + 
				"body.mp (                           $B_Aussenring                 ) = 1                       ! 0=manual; 1=auto (based on geometry); 2=mass manual, CG & Inertia auto\r\n" + 
				"body.I.tens (             1 ,   1 , $B_Aussenring                 ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
				"body.I.tens (             2 ,   2 , $B_Aussenring                 ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
				"body.I.tens (             3 ,   3 , $B_Aussenring                 ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
				"body.attr.2d.pos.x (                $B_Aussenring                 ) = -3.0000000000000000E+01\r\n" + 
				"body.attr.2d.pos.y (                $B_Aussenring                 ) = 3.0000000000000000E+01\r\n" + 
				"body.attr.2d.width (                $B_Aussenring                 ) = 1.1000000000000000E+02\r\n" + 
				"body.attr.2d.height (               $B_Aussenring                 ) = 8.0000000000000000E+01\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Aussenring_BRF             ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Aussenring_BRF             ) = $B_Aussenring           ! Body\r\n" + 
				"marker.flx.type (                   $M_Aussenring_BRF             ) = 4                       ! Flexible type\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Aussenring_Ref             ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Aussenring_Ref             ) = $B_Aussenring           ! Body\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Aussenring                 ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Aussenring                 ) = $B_Aussenring           ! Body\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Aussenring_Aussen          ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Aussenring_Aussen          ) = $B_Aussenring           ! Body\r\n" +
				"\r\n" +
				"marker.pos (              3 ,       $M_Aussenring_Aussen          ) = {-$G_GeometrieParameter.$_ZyRoLA_AR_LFB_B/2.0} ! Position\r\n" +
				"joint.from (                        $J_Aussenring                 ) = $M_Isys                 ! From Marker\r\n" + 
				"joint.to (                          $J_Aussenring                 ) = $M_Aussenring           ! To Marker\r\n" + 
				"joint.type (                        $J_Aussenring                 ) = 0                       ! Type\r\n" + 
				"joint.st.vel (            1 ,       $J_Aussenring                 ) = 0.0000000000000000E+00  ! Velocity\r\n" + 
				"joint.st.vel (            2 ,       $J_Aussenring                 ) = 0.0000000000000000E+00  ! Velocity\r\n" + 
				"joint.par (               1 ,       $J_Aussenring                 ) = 0.0000000000000000E+00  ! [rad] Rotation about alpha\r\n" + 
				"joint.par (               2 ,       $J_Aussenring                 ) = 0.0000000000000000E+00  ! [rad] Rotation about beta\r\n" + 
				"joint.par (               3 ,       $J_Aussenring                 ) = 0.0000000000000000E+00  ! [rad] Rotation about gamma\r\n" + 
				"joint.par (               4 ,       $J_Aussenring                 ) = 0.0000000000000000E+00  ! [m] Translation in x\r\n" + 
				"joint.par (               5 ,       $J_Aussenring                 ) = 0.0000000000000000E+00  ! [m] Translation in y\r\n" + 
				"joint.par (               6 ,       $J_Aussenring                 ) = 0.0000000000000000E+00  ! [m] Translation in z\r\n" + 
				"joint.par (               7 ,       $J_Aussenring                 ) = 0.0000000000000000E+00  ! [-] Rotation sequence\r\n" + 
				"joint.attr.2d.pos.x (     1 ,       $J_Aussenring                 ) = 20\r\n" + 
				"joint.attr.2d.pos.y (     1 ,       $J_Aussenring                 ) = 170\r\n" + 
				"joint.attr.2d.ori (       1 ,       $J_Aussenring                 ) = 270\r\n" + 
				"joint.attr.2d.paths.from.x (   1 ,   1 ,   1 , $J_Aussenring      ) = 20, 20\r\n" + 
				"joint.attr.2d.paths.from.y (   1 ,   1 ,   1 , $J_Aussenring      ) = 210, 187\r\n" + 
				"joint.attr.2d.paths.to.x (   1 ,   1 ,   1 , $J_Aussenring        ) = 20, 20\r\n" + 
				"joint.attr.2d.paths.to.y (   1 ,   1 ,   1 , $J_Aussenring        ) = 110, 153" +
				"\r\n" +
				"prim.type (                         $P_Aussenring_cuboid          ) = 2                       ! Type\r\n" + 
				"prim.ref (                          $P_Aussenring_cuboid          ) = $M_Aussenring_BRF       ! Reference Marker\r\n" + 
				"prim.ang (                1 ,       $P_Aussenring_cuboid          ) = { $G_sonstigeParameter.$_ZyRoLA_alpha } ! Angles\r\n" + 
				"prim.ang (                2 ,       $P_Aussenring_cuboid          ) = 0.0000000000000000E+00  ! Angles\r\n" + 
				"prim.color.r (            1 ,       $P_Aussenring_cuboid          ) = 1.4800000000000000E+02  ! Colors (red component)\r\n" + 
				"prim.color.r (            2 ,       $P_Aussenring_cuboid          ) = 1.4800000000000000E+02  ! Colors (red component)\r\n" + 
				"prim.color.g (            1 ,       $P_Aussenring_cuboid          ) = 1.4800000000000000E+02  ! Colors (green component)\r\n" + 
				"prim.color.g (            2 ,       $P_Aussenring_cuboid          ) = 1.4800000000000000E+02  ! Colors (green component)\r\n" + 
				"prim.color.b (            1 ,       $P_Aussenring_cuboid          ) = 1.4800000000000000E+02  ! Colors (blue component)\r\n" + 
				"prim.color.b (            2 ,       $P_Aussenring_cuboid          ) = 1.4800000000000000E+02  ! Colors (blue component)\r\n" + 
				"prim.color.t (            1 ,       $P_Aussenring_cuboid          ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.color.t (            2 ,       $P_Aussenring_cuboid          ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.shading (                      $P_Aussenring_cuboid          ) = 2                       ! Shading\r\n" + 
				"prim.drawstyle (                    $P_Aussenring_cuboid          ) = 2                       ! Draw style\r\n" + 
				"prim.transparency (                 $P_Aussenring_cuboid          ) = 2.5000000000000000E-01  ! Transparency\r\n"+
				"prim.mp.dens.solid (                $P_Aussenring_cuboid          ) = { $G_GeometrieMaterialdaten.$_ZyRoLA_AR_rho }  ! Density\r\n" + 
				"prim.par (                2 ,       $P_Aussenring_cuboid          ) = { $G_GeometrieParameter.$_ZyRoLA_AR_LFB_B } ! [m] Height\r\n" + 
				"prim.par (                3 ,       $P_Aussenring_cuboid          ) = { $G_GeometrieParameter.$_ZyRoLA_AR_A_D } ! [m] Outer diameter\r\n" + 
				"prim.par (                4 ,       $P_Aussenring_cuboid          ) = { $G_GeometrieParameter.$_ZyRoLA_AR_LFB_D } ! [m] Inner diameter\r\n" + 
				"prim.par (                5 ,       $P_Aussenring_cuboid          ) = 1.4400000000000000E+02  ! [-] Number of planes\r\n" + 
				"prim.par (                6 ,       $P_Aussenring_cuboid          ) = 0.0000000000000000E+00  ! [-] Number of highlighted planes\r\n" + 
				"prim.par (                7 ,       $P_Aussenring_cuboid          ) = 1.0000000000000000E+00  ! [-] Show bottom cap\r\n" + 
				"prim.par (                8 ,       $P_Aussenring_cuboid          ) = 1.0000000000000000E+00  ! [-] Show top cap\r\n" + 
				"prim.par (                9 ,       $P_Aussenring_cuboid          ) = 0.0000000000000000E+00  ! [rad] Start angle\r\n" + 
				"\r\n" +
				"\r\n";
				
	//Borde  case of 0 ="NU"(2 A), 1= "N" (2I), 2= "NJ" (2A/1I), 3 = "NUP" (2A/2I)
	
	String Borde_Vorlage = 
			"prim.type (                         $P_Bord%d           ) = 2                       ! Type\r\n" + 
			"prim.ref (                          $P_Bord%d           ) = %s       ! Reference Marker\r\n" + 
			"prim.pos (                3 ,       $P_Bord%d           ) = %s   ! Position\r\n" + 
			"prim.ang (                1 ,       $P_Bord%d           ) = { $G_sonstigeParameter.$_ZyRoLA_alpha } ! Angles\r\n" + 
			"prim.ang (                2 ,       $P_Bord%d           ) = 0.0000000000000000E+00  ! Angles\r\n" + 
			"prim.color.r (            1 ,       $P_Bord%d                     ) = 1.4800000000000000E+02  ! Colors (red component)\r\n" + 
			"prim.color.r (            2 ,       $P_Bord%d                     ) = 1.4800000000000000E+02  ! Colors (red component)\r\n" + 
			"prim.color.g (            1 ,       $P_Bord%d                     ) = 1.4800000000000000E+02  ! Colors (green component)\r\n" + 
			"prim.color.g (            2 ,       $P_Bord%d                     ) = 1.4800000000000000E+02  ! Colors (green component)\r\n" + 
			"prim.color.b (            1 ,       $P_Bord%d                     ) = 1.4800000000000000E+02  ! Colors (blue component)\r\n" + 
			"prim.color.b (            2 ,       $P_Bord%d                     ) = 1.4800000000000000E+02  ! Colors (blue component)\r\n" + 
			"prim.color.t (            1 ,       $P_Bord%d                     ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
			"prim.color.t (            2 ,       $P_Bord%d                     ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
			"prim.shading (                      $P_Bord%d                     ) = 2                       ! Shading\r\n" + 
			"prim.drawstyle (                    $P_Bord%d                     ) = 2                       ! Draw style\r\n" + 
			"prim.transparency (                 $P_Bord%d                     ) = 2.5000000000000000E-01  ! Transparency\r\n"+
			"prim.mp.dens.solid (                $P_Bord%d           ) = %s  ! Density\r\n" + 
			"prim.par (                2 ,       $P_Bord%d           ) = %s  ! [m] Height\r\n" + 
			"prim.par (                3 ,       $P_Bord%d           ) = %s  ! [m] Outer diameter\r\n" + 
			"prim.par (                4 ,       $P_Bord%d           ) = %s  ! [m] Inner diameter\r\n" + 
			"prim.par (                5 ,       $P_Bord%d           ) = 1.4400000000000000E+02  ! [-] Number of planes\r\n" + 
			"prim.par (                6 ,       $P_Bord%d           ) = 0.0000000000000000E+00  ! [-] Number of highlighted planes\r\n" + 
			"prim.par (                7 ,       $P_Bord%d           ) = 1.0000000000000000E+00  ! [-] Show bottom cap\r\n" + 
			"prim.par (                8 ,       $P_Bord%d           ) = 1.0000000000000000E+00  ! [-] Show top cap\r\n" + 
			"prim.par (                9 ,       $P_Bord%d           ) = 0.0000000000000000E+00  ! [rad] Start angle\r\n" +
			"\r\n";
				
	switch (i_case) {
	
	case -1: break;
	
	case 0: for (int i = 0; i<=2; i =i+2){
					String pos 	= "{"+ (i-1) +"*("+ var[0] +"-"+var[1]+")/2}" ;
					String bord_b	= var[1];
					String d_a		= var[2];
					String d_i		= var[3];
					String dichte 	= var[8];
					String Borde = String.format( Borde_Vorlage, i,i,"$M_Aussenring_BRF ",i,pos, i,i,i,i,i,i,i,i,i,i,i,i,i,i,dichte,i,bord_b,i, d_a,i, d_i,i,i,i,i,i);
					
					towrite = towrite + Borde;				
			}
			break;
			
	case 1: for (int i = 0; i<=2; i =i+2){
					String pos 	= "{"+ (i-1) +"*("+ var[4] +"-"+var[5]+")/2}" ;
					String bord_b	= var[5];
					String d_a		= var[7];
					String d_i		= var[6];
					String dichte 	= var[9];
					String Borde = String.format( Borde_Vorlage, i,i,"$M_Innenring_BRF",i,pos, i,i,i,i,i,i,i,i,i,i,i,i,i,i,dichte,i,bord_b,i, d_a,i, d_i,i,i,i,i,i);
					
					towrite = towrite + Borde;				
			}
			break;
			
	case 2:	String pos 	= "{("+ var[4] +"-"+var[5]+")/2}" ;
			String bord_b	= var[5];
			String d_a		= var[7];
			String d_i		= var[6];
			String Borde = String.format( Borde_Vorlage, 4,4,"$M_Innenring",4,pos, 4,4,4,4,4,4,4,4,4,4,4,4,4,4,var[9],4,bord_b,4, d_a,4, d_i,4,4,4,4,4);
			
			towrite = towrite + Borde;
			
			 for (int i = 0; i<=2; i =i+2){
					 pos 	= "{"+ (i-1) +"*("+ var[0] +"-"+var[1]+")/2}" ;
					 bord_b		= var[1];
					 d_a		= var[2];
					 d_i		= var[3];
					 String dichte 	= var[8];
					 Borde = String.format( Borde_Vorlage, i,i,"$M_Aussenring_BRF ",i,pos, i,i,i,i,i,i,i,i,i,i,i,i,i,i,dichte,i,bord_b,i, d_a,i, d_i,i,i,i,i,i);
					
					towrite = towrite + Borde;				
			}
		
			break;
			
	case 3: for (int i = 0; i<=2; i =i+2){
					pos	= "{"+ (i-1) +"*("+ var[0] +"-"+var[1]+")/2}" ;
					bord_b	= var[1];
					d_a		= var[2];
					d_i		= var[3];
					String dichte 	= var[8];
					Borde = String.format( Borde_Vorlage, i,i,"$M_Aussenring_BRF ",i,pos,i,i,i,i,i,i,i,i,i,i,i,i,i,i,dichte,i,bord_b,i, d_a,i, d_i,i,i,i,i,i);
					
					towrite = towrite + Borde;				
			}
		 
			for (int i = 4; i<=6; i =i+2){
					pos 	= "{"+ (i-5) +"*("+ var[4] +"-"+var[5]+")/2}" ;
					bord_b	= var[5];
					d_a		= var[7];
					d_i		= var[6];
					String dichte = var[9];
					Borde = String.format( Borde_Vorlage, i,i,"$M_Innenring_BRF",i,pos, i,i,i,i,i,i,i,i,i,i,i,i,i,i,dichte,i,bord_b,i, d_a,i, d_i,i,i,i,i,i);
					
					towrite = towrite + Borde;				
			}
		
			break;				
	
	}

	
	
	
	
	
	
				
				
				
				
				// Innenring
	towrite = towrite +
			
				"\r\n" +
				"\r\n" +				
				"body.m (                            $B_Innenring                  ) = 1.0000000000000000E+00  ! Mass of the Body\r\n" + 
				"body.mp (                           $B_Innenring                  ) = 1                       ! 0=manual; 1=auto (based on geometry); 2=mass manual, CG & Inertia auto\r\n" + 
				"body.I.tens (             1 ,   1 , $B_Innenring                  ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
				"body.I.tens (             2 ,   2 , $B_Innenring                  ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
				"body.I.tens (             3 ,   3 , $B_Innenring                  ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
				"body.attr.2d.pos.x (                $B_Innenring                  ) = 2.7000000000000000E+02\r\n" + 
				"body.attr.2d.pos.y (                $B_Innenring                  ) = -2.0000000000000000E+01\r\n" + 
				"body.attr.2d.width (                $B_Innenring                  ) = 5.0000000000000000E+01\r\n" + 
				"body.attr.2d.height (               $B_Innenring                  ) = 3.0000000000000000E+01\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Innenring_BRF              ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Innenring_BRF              ) = $B_Innenring            ! Body\r\n" + 
				"marker.flx.type (                   $M_Innenring_BRF              ) = 4                       ! Flexible type\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Innenring_Ref              ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Innenring_Ref              ) = $B_Innenring            ! Body\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Innenring                  ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Innenring                  ) = $B_Innenring            ! Body\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Innenring_Aussen           ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Innenring_Aussen           ) = $B_Innenring            ! Body\r\n" + 
				"marker.pos (              3 ,       $M_Innenring_Aussen           ) = {-$G_GeometrieParameter.$_ZyRoLA_IR_LFB_B/2.0} ! Position" +
				"\r\n";
	if (ZyrolaGui.s_lagermod == 0) {
		
		towrite = towrite +	
				"joint.from (                        $J_Innenring                  ) = $M_Innenring_halter     ! From Marker\r\n" + 
				"joint.to (                          $J_Innenring                  ) = $M_Innenring            ! To Marker\r\n" + 
				"joint.type (                        $J_Innenring                  ) = 40                      ! Type\r\n" + 
				"joint.st.pos (            1 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"joint.st.pos (            2 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"joint.st.vel (            1 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! Velocity\r\n" + 
				"joint.st.vel (            2 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! Velocity\r\n" + 
				"joint.st.vel (            3 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! Velocity\r\n" + 
				"joint.st.dep (            2 ,       $J_Innenring                  ) = 0                       ! Dependency state\r\n" + 
				"joint.st.equi (           1 ,       $J_Innenring                  ) = 0                       ! Equilibrium\r\n" + 
				"joint.st.equi (           2 ,       $J_Innenring                  ) = 2                       ! Equilibrium\r\n" + 
				"joint.par (               1 ,       $J_Innenring                  ) = 3.0000000000000000E+00  ! [-] Axis of Motion\r\n" + 
				"joint.par (               2 ,       $J_Innenring                  ) = $UVectorElement_U1      ! [-] Time Excitation-ID for s(t)\r\n" + 
				"joint.par (               3 ,       $J_Innenring                  ) = $UVectorElement_U2      ! [-] Time Excitation-ID for sp(t)\r\n" + 
				"joint.par (               4 ,       $J_Innenring                  ) = $UVectorElement_U3      ! [-] Time Excitation-ID for spp(t)\r\n" + 
				"joint.par (               5 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! [rad] Constant Offset"+
				"\r\n"+
 				"\r\n";
	} else if (ZyrolaGui.s_lagermod == 1) {
		
		towrite = towrite +
				"joint.from (                        $J_Innenring                  ) = $M_GROUND               ! From Marker\r\n" + 
				"joint.to (                          $J_Innenring                  ) = $M_Innenring            ! To Marker\r\n" + 
				"joint.type (                        $J_Innenring                  ) = 0                       ! Type\r\n" + 
				"joint.st.pos (            1 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"joint.st.pos (            2 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"joint.st.vel (            1 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! Velocity\r\n" + 
				"joint.st.vel (            2 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! Velocity\r\n" + 
				"joint.st.vel (            3 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! Velocity\r\n" + 
				"joint.st.dep (            2 ,       $J_Innenring                  ) = 0                       ! Dependency state\r\n" + 
				"joint.st.equi (           1 ,       $J_Innenring                  ) = 0                       ! Equilibrium\r\n" + 
				"joint.st.equi (           2 ,       $J_Innenring                  ) = 2                       ! Equilibrium\r\n" + 
				"joint.par (               1 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! [rad] Rotation about alpha\r\n" + 
				"joint.par (               2 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! [rad] Rotation about beta\r\n" + 
				"joint.par (               3 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! [rad] Rotation about gamma\r\n" + 
				"joint.par (               4 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! [m] Translation in x\r\n" + 
				"joint.par (               5 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! [m] Translation in y\r\n" + 
				"joint.par (               6 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! [m] Translation in z\r\n" + 
				"joint.par (               7 ,       $J_Innenring                  ) = 0.0000000000000000E+00  ! [-] Rotation sequence" +
				"\r\n"+
 				"\r\n";
	

	
	}
				
			
		towrite = towrite +		
				"\r\n"+
 				"\r\n"+
				"prim.type (                         $P_Innenring_cuboid           ) = 2                       ! Type\r\n" + 
				"prim.ref (                          $P_Innenring_cuboid           ) = $M_Innenring_BRF        ! Reference Marker\r\n" + 
				"prim.ang (                1 ,       $P_Innenring_cuboid           ) = { $G_sonstigeParameter.$_ZyRoLA_alpha } ! Angles\r\n" + 
				"prim.color.r (            1 ,       $P_Innenring_cuboid           ) = 6.4000000000000000E+01  ! Colors (red component)\r\n" + 
				"prim.color.r (            2 ,       $P_Innenring_cuboid           ) = 6.4000000000000000E+01  ! Colors (red component)\r\n" + 
				"prim.color.g (            1 ,       $P_Innenring_cuboid           ) = 6.4000000000000000E+01  ! Colors (green component)\r\n" + 
				"prim.color.g (            2 ,       $P_Innenring_cuboid           ) = 6.4000000000000000E+01  ! Colors (green component)\r\n" + 
				"prim.color.b (            1 ,       $P_Innenring_cuboid           ) = 6.4000000000000000E+01  ! Colors (blue component)\r\n" + 
				"prim.color.b (            2 ,       $P_Innenring_cuboid           ) = 6.4000000000000000E+01  ! Colors (blue component)\r\n" + 
				"prim.color.t (            1 ,       $P_Innenring_cuboid           ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.color.t (            2 ,       $P_Innenring_cuboid           ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.shading (                      $P_Innenring_cuboid           ) = 2                       ! Shading\r\n" + 
				"prim.drawstyle (                    $P_Innenring_cuboid           ) = 2                       ! Draw style\r\n" + 
				"prim.transparency (                 $P_Innenring_cuboid           ) = 2.5000000000000000E-01  ! Transparency\r\n"+
				"prim.mp.dens.solid (                $P_Innenring_cuboid           ) = { $G_GeometrieMaterialdaten.$_ZyRoLA_IR_rho }  ! Density\r\n" + 
				"prim.par (                2 ,       $P_Innenring_cuboid           ) = { $G_GeometrieParameter.$_ZyRoLA_IR_LFB_B } ! [m] Height\r\n" + 
				"prim.par (                3 ,       $P_Innenring_cuboid           ) = { $G_GeometrieParameter.$_ZyRoLA_IR_LFB_D } ! [m] Outer diameter\r\n" + 
				"prim.par (                4 ,       $P_Innenring_cuboid           ) = { $G_GeometrieParameter.$_ZyRoLA_IR_I_D } ! [m] Inner diameter\r\n" + 
				"prim.par (                5 ,       $P_Innenring_cuboid           ) = 1.4400000000000000E+02  ! [-] Number of planes\r\n" + 
				"prim.par (                6 ,       $P_Innenring_cuboid           ) = 0.0000000000000000E+00  ! [-] Number of highlighted planes\r\n" + 
				"prim.par (                7 ,       $P_Innenring_cuboid           ) = 1.0000000000000000E+00  ! [-] Show bottom cap\r\n" + 
				"prim.par (                8 ,       $P_Innenring_cuboid           ) = 1.0000000000000000E+00  ! [-] Show top cap\r\n" + 
				"prim.par (                9 ,       $P_Innenring_cuboid           ) = 0.0000000000000000E+00  ! [rad] Start angle\r\n" + 
				"\r\n" + 
				"\r\n";
		
		if (ZyrolaGui.s_lagermod == 0) {
			
			towrite = towrite +
				
				"body.m (                            $B_Innenring_halter           ) = 1.0000000117000000E-07  ! Mass of the Body\r\n" + 
				"body.mp (                           $B_Innenring_halter           ) = 0                       ! 0=manual; 1=auto (based on geometry); 2=mass manual, CG & Inertia auto\r\n" + 
				"body.I.tens (             1 ,   1 , $B_Innenring_halter           ) = 1.0000000000000000E-07  ! Moments of inertia\r\n" + 
				"body.I.tens (             2 ,   2 , $B_Innenring_halter           ) = 1.0000000000000000E-07  ! Moments of inertia\r\n" + 
				"body.I.tens (             3 ,   3 , $B_Innenring_halter           ) = 1.0000000000000000E-07  ! Moments of inertia\r\n" + 
				"body.I.kind (                       $B_Innenring_halter           ) = -1                      ! Kind of I-tensor specification: -1=wrt CG; 0=wrt BRF; 1=wrt Marker\r\n" + 
				"body.attr.2d.pos.x (                $B_Innenring_halter           ) = 3.7000000000000000E+02\r\n" + 
				"body.attr.2d.pos.y (                $B_Innenring_halter           ) = -2.0000000000000000E+01\r\n" + 
				"body.attr.2d.width (                $B_Innenring_halter           ) = 5.0000000000000000E+01\r\n" + 
				"body.attr.2d.height (               $B_Innenring_halter           ) = 3.0000000000000000E+01\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Innenring_halter_BRF       ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Innenring_halter_BRF       ) = $B_Innenring_halter     ! Body\r\n" + 
				"marker.flx.type (                   $M_Innenring_halter_BRF       ) = 4                       ! Flexible type\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Innenring_halter           ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Innenring_halter           ) = $B_Innenring_halter     ! Body\r\n" + 
				"\r\n" + 
				"joint.from (                        $J_Innenring_halter           ) = $M_Isys                 ! From Marker\r\n" + 
				"joint.to (                          $J_Innenring_halter           ) = $M_Innenring_halter     ! To Marker\r\n" + 
				"joint.type (                        $J_Innenring_halter           ) = 25                      ! Type\r\n" + 
				"joint.st.dep (            2 ,       $J_Innenring_halter           ) = 0                       ! Dependency state\r\n" + 
				"joint.par (               1 ,       $J_Innenring_halter           ) = 0.0000000000000000E+00  ! [-] Axis of Rotation    1\r\n" + 
				"joint.par (               2 ,       $J_Innenring_halter           ) = 0.0000000000000000E+00  ! [-] Axis of Rotation    2\r\n" + 
				"joint.par (               3 ,       $J_Innenring_halter           ) = 0.0000000000000000E+00  ! [-] Axis of Rotation    3\r\n" + 
				"joint.par (               4 ,       $J_Innenring_halter           ) = 1.0000000000000000E+00  ! [-] Axis of Translation 1\r\n" + 
				"joint.par (               5 ,       $J_Innenring_halter           ) = 2.0000000000000000E+00  ! [-] Axis of Translation 2\r\n" + 
				"joint.par (               6 ,       $J_Innenring_halter           ) = "+ZyrolaGui.j_innenring_halter_joint_def_transz+" ! [-] Axis of Translation 3\r\n" + 
				"joint.par (               8 ,       $J_Innenring_halter           ) = 0.0000000000000000E+00  ! [-] Trans-Rot Sequence:\r\n" + 	
				"\r\n" + 
				"\r\n" +
				"\r\n" + 
				"\r\n" ;
			
		} else if(ZyrolaGui.s_lagermod == 1){
			towrite = towrite + 
					"body.m (                            $B_GROUND                     ) = 1.0000000117000000E-07  ! Mass of the Body\r\n" + 
					"body.mp (                           $B_GROUND                     ) = 0                       ! 0=manual; 1=auto (based on geometry); 2=mass manual, CG & Inertia auto\r\n" + 
					"body.I.tens (             1 ,   1 , $B_GROUND                     ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
					"body.I.tens (             2 ,   2 , $B_GROUND                     ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
					"body.I.tens (             3 ,   3 , $B_GROUND                     ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
					"body.I.kind (                       $B_GROUND                     ) = -1                      ! Kind of I-tensor specification: -1=wrt CG; 0=wrt BRF; 1=wrt Marker\r\n" + 
					"body.attr.2d.pos.x (                $B_GROUND                     ) = 3.7000000000000000E+02\r\n" + 
					"body.attr.2d.pos.y (                $B_GROUND                     ) = -2.0000000000000000E+01\r\n" + 
					"body.attr.2d.width (                $B_GROUND                     ) = 5.0000000000000000E+01\r\n" + 
					"body.attr.2d.height (               $B_GROUND                     ) = 3.0000000000000000E+01\r\n" + 
					"\r\n" + 
					"marker.type (                       $M_GROUND_BRF                 ) = 1                       ! Type\r\n" + 
					"marker.parent (                     $M_GROUND_BRF                 ) = $B_GROUND               ! Body\r\n" + 
					"marker.flx.type (                   $M_GROUND_BRF                 ) = 4                       ! Flexible type\r\n" + 
					"\r\n" + 
					"marker.type (                       $M_GROUND                     ) = 1                       ! Type\r\n" + 
					"marker.parent (                     $M_GROUND                     ) = $B_GROUND               ! Body\r\n" + 
					"\r\n" + 
					"joint.from (                        $J_GROUND                     ) = $M_Isys                 ! From Marker\r\n" + 
					"joint.to (                          $J_GROUND                     ) = $M_GROUND               ! To Marker\r\n" + 
					"joint.type (                        $J_GROUND                     ) = 0                       ! Type\r\n" + 
					"joint.st.dep (            2 ,       $J_GROUND                     ) = 0                       ! Dependency state\r\n" + 
					"joint.par (               1 ,       $J_GROUND                     ) = 0.0000000000000000E+00  ! [rad] Rotation about alpha\r\n" + 
					"joint.par (               2 ,       $J_GROUND                     ) = 0.0000000000000000E+00  ! [rad] Rotation about beta\r\n" + 
					"joint.par (               3 ,       $J_GROUND                     ) = 0.0000000000000000E+00  ! [rad] Rotation about gamma\r\n" + 
					"joint.par (               4 ,       $J_GROUND                     ) = 0.0000000000000000E+00  ! [m] Translation in x\r\n" + 
					"joint.par (               5 ,       $J_GROUND                     ) = 0.0000000000000000E+00  ! [m] Translation in y\r\n" + 
					"joint.par (               6 ,       $J_GROUND                     ) = 0.0000000000000000E+00  ! [m] Translation in z\r\n" + 
					"joint.par (               7 ,       $J_GROUND                     ) = 0.0000000000000000E+00  ! [-] Rotation sequence\r\n" + 
					"\r\n" + 
					"prim.type (                         $P_GROUND_Punkt               ) = 15                      ! Type\r\n" + 
					"prim.ref (                          $P_GROUND_Punkt               ) = $M_GROUND               ! Reference Marker\r\n" + 
					"prim.color.r (            1 ,       $P_GROUND_Punkt               ) = 2.5500000000000000E+02  ! Colors (red component)\r\n" + 
					"prim.color.r (            2 ,       $P_GROUND_Punkt               ) = 2.5500000000000000E+02  ! Colors (red component)\r\n" + 
					"prim.color.g (            1 ,       $P_GROUND_Punkt               ) = 0.0000000000000000E+00  ! Colors (green component)\r\n" + 
					"prim.color.g (            2 ,       $P_GROUND_Punkt               ) = 0.0000000000000000E+00  ! Colors (green component)\r\n" + 
					"prim.color.b (            1 ,       $P_GROUND_Punkt               ) = 0.0000000000000000E+00  ! Colors (blue component)\r\n" + 
					"prim.color.b (            2 ,       $P_GROUND_Punkt               ) = 0.0000000000000000E+00  ! Colors (blue component)\r\n" + 
					"prim.color.t (            1 ,       $P_GROUND_Punkt               ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
					"prim.color.t (            2 ,       $P_GROUND_Punkt               ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
					"prim.par (                2 ,       $P_GROUND_Punkt               ) = 5.0000002375000002E-04  ! [m] Radius\r\n" + 
					"prim.par (                3 ,       $P_GROUND_Punkt               ) = { 0.39270699024 deg }   ! [rad] Start angle\r\n" + 
					"prim.par (                4 ,       $P_GROUND_Punkt               ) = { 0 deg }               ! [rad] Delta angle\r\n" + 
					"prim.par (                5 ,       $P_GROUND_Punkt               ) = 2.0000000000000000E+01  ! [-] Number of meridians"+
					"\r\n" + 
					"\r\n" +
					"\r\n" + 
					"\r\n" ;
			
		}


	
	
	//!!Kaefig!!
	

																																			
	//Schleife für virtuellen  Kaefig
	
	if ((ZyrolaGui.kaefigindex == 0)) {
		
		towrite = towrite +
				"body.m (                            $B_Kaefig                     ) = 1.0000000000000000E+00  ! Mass of the Body\r\n" +
				"body.mp (                           $B_Kaefig                 	   ) = 1                       ! 0=manual; 1=auto (based on geometry); 2=mass manual, CG & Inertia auto\r\n" + 
				"body.I.tens (             1 ,   1 , $B_Kaefig                     ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
				"body.I.tens (             2 ,   2 , $B_Kaefig                     ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
				"body.I.tens (             3 ,   3 , $B_Kaefig                     ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
				"body.I.kind (                       $B_Kaefig                     ) = -1                      ! Kind of I-tensor specification: -1=wrt CG; 0=wrt BRF; 1=wrt Marker\r\n" + 
				"body.attr.2d.pos.x (                $B_Kaefig                     ) = 7.2000000000000000E+02\r\n" + 
				"body.attr.2d.pos.y (                $B_Kaefig                     ) = -2.0000000000000000E+01\r\n" + 
				"body.attr.2d.width (                $B_Kaefig                     ) = 5.0000000000000000E+01\r\n" + 
				"body.attr.2d.height (               $B_Kaefig                     ) = 3.0000000000000000E+01\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Kaefig_BRF                 ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Kaefig_BRF                 ) = $B_Kaefig               ! Body\r\n" + 
				"marker.flx.type (                   $M_Kaefig_BRF                 ) = 4                       ! Flexible type\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Kaefig                     ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Kaefig                     ) = $B_Kaefig               ! Body\r\n" + 
				"\r\n"+
				"joint.from (                        $J_Kaefig                     ) = $M_Innenring            ! From Marker\r\n" + 
				"joint.to (                          $J_Kaefig                     ) = $M_Kaefig               ! To Marker\r\n" + 
				"joint.type (                        $J_Kaefig                     ) = 25                      ! Type\r\n" ; 
		
				if(ZyrolaGui.s_lagermod == 0) towrite = towrite + "joint.st.vel (            1 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! Velocity\r\n";
				if(ZyrolaGui.s_lagermod == 1) towrite = towrite + "joint.st.vel (            1 ,       $J_Kaefig                     ) = {$G_Simulationsparameter.$_ZyRoLa_sim_dreh_KF - ($G_Simulationsparameter.$_ZyRoLa_sim_dreh_IR/60*2*3.14)}  ! Velocity\r\n";						
		towrite = towrite +
				"joint.par (               1 ,       $J_Kaefig                     ) = 3.0000000000000000E+00  ! [-] Axis of Rotation    1\r\n" + 
				"joint.par (               2 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! [-] Axis of Rotation    2\r\n" + 
				"joint.par (               3 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! [-] Axis of Rotation    3\r\n" + 
				"joint.par (               4 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! [-] Axis of Translation 1\r\n" + 
				"joint.par (               5 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! [-] Axis of Translation 2\r\n" + 
				"joint.par (               6 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! [-] Axis of Translation 3\r\n" + 
				"joint.par (               8 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! [-] Trans-Rot Sequence:\r\n" + 

				"\r\n" + 
				"prim.type (                         $P_Kaefig_Geom                ) = 2                       ! Type\r\n" + 
				"prim.ref (                          $P_Kaefig_Geom                ) = $M_Kaefig               ! Reference Marker\r\n" + 
				"prim.ang (                2 ,       $P_Kaefig_Geom                ) = { 90deg }               ! Angles\r\n" + 
				"prim.ang (                3 ,       $P_Kaefig_Geom                ) = { 90deg }               ! Angles\r\n" + 
				"prim.color.r (            1 ,       $P_Kaefig_Geom                ) = 1.2800000000000000E+02  ! Colors (red component)\r\n" + 
				"prim.color.r (            2 ,       $P_Kaefig_Geom                ) = 1.2800000000000000E+02  ! Colors (red component)\r\n" + 
				"prim.color.g (            1 ,       $P_Kaefig_Geom                ) = 1.2800000000000000E+02  ! Colors (green component)\r\n" + 
				"prim.color.g (            2 ,       $P_Kaefig_Geom                ) = 1.2800000000000000E+02  ! Colors (green component)\r\n" + 
				"prim.color.b (            1 ,       $P_Kaefig_Geom                ) = 1.2800000000000000E+02  ! Colors (blue component)\r\n" + 
				"prim.color.b (            2 ,       $P_Kaefig_Geom                ) = 1.2800000000000000E+02  ! Colors (blue component)\r\n" + 
				"prim.color.t (            1 ,       $P_Kaefig_Geom                ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.color.t (            2 ,       $P_Kaefig_Geom                ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" +
				"prim.mp.dens.solid (                $P_Kaefig_Geom                ) = 7.8500000000000000E+03  ! Density\r\n"+
				"prim.par (                2 ,       $P_Kaefig_Geom                ) = {$G_GeometrieParameter.$_ZyRoLA_AR_LFB_B}  ! [m] Height\r\n" + 
				"prim.par (                3 ,       $P_Kaefig_Geom                ) = {$G_GeometrieParameter.$_ZyRoLA_AR_LFB_B / 2} ! [m] Outer diameter\r\n" + 
				"prim.par (                4 ,       $P_Kaefig_Geom                ) = 0.0000000000000000E+00  ! [m] Inner diameter\r\n" + 
				"prim.par (                5 ,       $P_Kaefig_Geom                ) = 1.2000000000000000E+01  ! [-] Number of planes\r\n" + 
				"prim.par (                6 ,       $P_Kaefig_Geom                ) = 0.0000000000000000E+00  ! [-] Number of highlighted planes\r\n" + 
				"prim.par (                7 ,       $P_Kaefig_Geom                ) = 1.0000000000000000E+00  ! [-] Show bottom cap\r\n" + 
				"prim.par (                8 ,       $P_Kaefig_Geom                ) = 1.0000000000000000E+00  ! [-] Show top cap\r\n" + 
				"prim.par (                9 ,       $P_Kaefig_Geom                ) = 0.0000000000000000E+00  ! [rad] Start angle\r\n" + 
				"prim.par (               10 ,       $P_Kaefig_Geom                ) = 0.0000000000000000E+00  ! [rad] Delta angle\r\n" + 
				"prim.par (               11 ,       $P_Kaefig_Geom                ) = 0.0000000000000000E+00  ! [-] Close geometry with";
		
		
		
		
		
	
		for (int i=1; i<= ZyrolaGui.wk_N; i++) {
			
			String bez_K_M 		= "$M_Kaefig" + i;
			String bez_K_P 		= "$P_Kaefig_Geom" +i;
			
			
			
			towrite = towrite +
					"marker.type (                       " + bez_K_M +"                   ) = 2                       ! Type\r\n" + 
					"marker.parent (                     " + bez_K_M +"                   ) = $B_Kaefig               ! Body\r\n" + 
					"marker.pos (              1 ,       " + bez_K_M +"                   ) = { $G_WK_PositionsDaten.$_ZyRoLA_WK" +i+ "_x } ! Position\r\n" + 
					"marker.pos (              2 ,       " + bez_K_M +"                   ) = { $G_WK_PositionsDaten.$_ZyRoLA_WK" +i+ "_y } ! Position\r\n" + 
					"marker.ang (              3 ,       " + bez_K_M +"                   ) = { $G_WK_PositionsDaten.$_ZyRoLA_gamma" +i+" } ! Angles\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"prim.type (                         " + bez_K_P + "               ) = 14                      ! Type\r\n" + 
					"prim.ref (                          " + bez_K_P + "               ) = " + bez_K_M +"          ! Reference Marker\r\n" + 
					"prim.ang (                2 ,       " + bez_K_P + "               ) = { 90deg }               ! Angles\r\n" + 
					"prim.color.r (            1 ,       " + bez_K_P + "               ) = 1.2800000000000000E+02  ! Colors (red component)\r\n" + 
					"prim.color.g (            1 ,       " + bez_K_P + "               ) = 0.0000000000000000E+00  ! Colors (green component)\r\n" + 
					"prim.color.b (            1 ,       " + bez_K_P + "               ) = 0.0000000000000000E+00  ! Colors (blue component)\r\n" + 
					"prim.color.t (            1 ,       " + bez_K_P + "               ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" +
					"prim.mp.incl (                      " + bez_K_P + "               ) = 0                       ! Include Primitive in computation\r\n" +
					"prim.par (                2 ,       " + bez_K_P + "               ) = 2.0000000000000000E+00  ! [-] Direction\r\n" + 
					"prim.par (                3 ,       " + bez_K_P + "               ) = {($G_GeometrieParameter.$_ZyRoLA_AR_LFB_D+$G_GeometrieParameter.$_ZyRoLA_IR_LFB_D)/4}  ! [m] Length\r\n"+
					"prim.mp.dens.solid (                " + bez_K_P + "           	 ) = 7.8500000000000000E+03  ! Density\r\n" +
					"\r\n"+
					"\r\n";
		}	
		
	}

	//Schleife für Taschenfederkäfig
	
	if ( (ZyrolaGui.kaefigindex == 1)) {
		
		
		towrite = towrite +
				"body.m (                            $B_Kaefig                    ) = 2.0000000000000001E-01  ! Mass of the Body\r\n" + 
				"body.mp (                           $B_Kaefig                    ) = 1                       ! 0=manual; 1=auto (based on geometry); 2=mass manual, CG & Inertia auto\r\n" + 
				"body.I.tens (             1 ,   1 , $B_Kaefig                    ) = 1.0000000000000000E-03  ! Moments of inertia\r\n" + 
				"body.I.tens (             2 ,   2 , $B_Kaefig                    ) = 1.0000000000000000E-03  ! Moments of inertia\r\n" + 
				"body.I.tens (             3 ,   3 , $B_Kaefig                    ) = 1.0000000000000000E-04  ! Moments of inertia\r\n" + 
				"body.I.kind (                       $B_Kaefig                    ) = -1                      ! Kind of I-tensor specification: -1=wrt CG; 0=wrt BRF; 1=wrt Marker\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Kaefig_BRF                ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Kaefig_BRF                ) = $B_Kaefig              ! Body\r\n" + 
				"marker.flx.type (                   $M_Kaefig_BRF                ) = 4                       ! Flexible type\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Kaefig                    ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Kaefig                    ) = $B_Kaefig              ! Body\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Kaefig_Aussen             ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Kaefig_Aussen             ) = $B_Kaefig              ! Body\r\n" + 
				"marker.pos (              3 ,       $M_Kaefig_Aussen             ) = {-($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_Breite/2)} ! Position\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Kaefig_Ref                ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Kaefig_Ref                ) = $B_Kaefig              ! Body\r\n" + 
				"marker.pos (              3 ,       $M_Kaefig_Ref                ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"\r\n" +
				"\r\n" +
				"\r\n" +
				"joint.from (                        $J_Kaefig                     ) = $M_Innenring            ! From Marker\r\n" + 
				"joint.to (                          $J_Kaefig                     ) = $M_Kaefig               ! To Marker\r\n" + 
				"joint.type (                        $J_Kaefig                     ) = 25                      ! Type\r\n";
		
				if(ZyrolaGui.s_lagermod == 0) towrite = towrite + "joint.st.vel (            1 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! Velocity\r\n";
				if(ZyrolaGui.s_lagermod == 1) towrite = towrite + "joint.st.vel (            1 ,       $J_Kaefig                     ) = {$G_Simulationsparameter.$_ZyRoLa_sim_dreh_KF - ($G_Simulationsparameter.$_ZyRoLa_sim_dreh_IR/60*2*3.14)}  ! Velocity\r\n";						
		towrite = towrite +
				"joint.par (               1 ,       $J_Kaefig                     ) = 3.0000000000000000E+00  ! [-] Axis of Rotation    1\r\n" + 
				"joint.par (               2 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! [-] Axis of Rotation    2\r\n" + 
				"joint.par (               3 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! [-] Axis of Rotation    3\r\n" + 
				"joint.par (               4 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! [-] Axis of Translation 1\r\n" + 
				"joint.par (               5 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! [-] Axis of Translation 2\r\n" + 
				"joint.par (               6 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! [-] Axis of Translation 3\r\n" + 
				"joint.par (               8 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! [-] Trans-Rot Sequence:\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"prim.type (                         $P_Kaefig                    ) = 2                       ! Type\r\n" + 
				"prim.ref (                          $P_Kaefig                    ) = $M_Kaefig              ! Reference Marker\r\n" + 
				"prim.pos (                3 ,       $P_Kaefig                    ) = { (($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_Breite/2+0.1*$G_GeometrieParameter.$_ZyRoLA_WK_z))} ! Position\r\n" + 
				"prim.ang (                1 ,       $P_Kaefig                    ) = $G_sonstigeParameter.$_ZyRoLA_alpha          ! Angles\r\n" + 
				"prim.color.r (            1 ,       $P_Kaefig                    ) = 1.2800000000000000E+02  ! Colors (red component)\r\n" + 
				"prim.color.r (            2 ,       $P_Kaefig                    ) = 1.2800000000000000E+02  ! Colors (red component)\r\n" + 
				"prim.color.g (            1 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! Colors (green component)\r\n" + 
				"prim.color.g (            2 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! Colors (green component)\r\n" + 
				"prim.color.b (            1 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! Colors (blue component)\r\n" + 
				"prim.color.b (            2 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! Colors (blue component)\r\n" + 
				"prim.color.t (            1 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.color.t (            2 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.mp.dens.solid (                $P_Kaefig                	  ) = {$G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_rho}  ! Density\r\n"+
				"prim.par (                2 ,       $P_Kaefig                    ) = {(0.1*$G_GeometrieParameter.$_ZyRoLA_WK_z)}         ! [m] Height\r\n" + 
				"prim.par (                3 ,       $P_Kaefig                    ) = { ($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_aussen) } ! [m] Outer diameter\r\n" + 
				"prim.par (                4 ,       $P_Kaefig                    ) = { ($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_innen) } ! [m] Inner diameter\r\n" + 
				"prim.par (                5 ,       $P_Kaefig                    ) = 4.8000000000000000E+01  ! [-] Number of planes\r\n" + 
				"prim.par (                6 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! [-] Number of highlighted planes\r\n" + 
				"prim.par (                7 ,       $P_Kaefig                    ) = 1.0000000000000000E+00  ! [-] Show bottom cap\r\n" + 
				"prim.par (                8 ,       $P_Kaefig                    ) = 1.0000000000000000E+00  ! [-] Show top cap\r\n" + 
				"prim.par (                9 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! [rad] Start angle\r\n" + 
				"\r\n" + 
				"prim.type (                         $P_KaefigR2                  ) = 2                       ! Type\r\n" + 
				"prim.ref (                          $P_KaefigR2                  ) = $M_Kaefig              ! Reference Marker\r\n" + 
				"prim.pos (                3 ,       $P_KaefigR2                  ) = { -(($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_Breite/2+0.1*$G_GeometrieParameter.$_ZyRoLA_WK_z)) } ! Position\r\n" + 
				"prim.ang (                1 ,       $P_KaefigR2                  ) = $G_sonstigeParameter.$_ZyRoLA_alpha          ! Angles\r\n" + 
				"prim.color.r (            1 ,       $P_KaefigR2                  ) = 1.2800000000000000E+02  ! Colors (red component)\r\n" + 
				"prim.color.r (            2 ,       $P_KaefigR2                  ) = 1.2800000000000000E+02  ! Colors (red component)\r\n" + 
				"prim.color.g (            1 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! Colors (green component)\r\n" + 
				"prim.color.g (            2 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! Colors (green component)\r\n" + 
				"prim.color.b (            1 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! Colors (blue component)\r\n" + 
				"prim.color.b (            2 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! Colors (blue component)\r\n" + 
				"prim.color.t (            1 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.color.t (            2 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.mp.dens.solid (                $P_KaefigR2                	) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_rho  ! Density\r\n"+
				"prim.par (                2 ,       $P_KaefigR2                  ) = {(0.1*$G_GeometrieParameter.$_ZyRoLA_WK_z)}           ! [m] Height\r\n" + 
				"prim.par (                3 ,       $P_KaefigR2                  ) = { ($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_aussen) } ! [m] Outer diameter\r\n" + 
				"prim.par (                4 ,       $P_KaefigR2                  ) = { ($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_innen) } ! [m] Inner diameter\r\n" + 
				"prim.par (                5 ,       $P_KaefigR2                  ) = 4.8000000000000000E+01  ! [-] Number of planes\r\n" + 
				"prim.par (                6 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! [-] Number of highlighted planes\r\n" + 
				"prim.par (                7 ,       $P_KaefigR2                  ) = 1.0000000000000000E+00  ! [-] Show bottom cap\r\n" + 
				"prim.par (                8 ,       $P_KaefigR2                  ) = 1.0000000000000000E+00  ! [-] Show top cap\r\n" + 
				"prim.par (                9 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! [rad] Start angle\r\n" + 
				"\r\n"+
				"\r\n"+
				"\r\n";
	
	
	for (int i=1; i <= ZyrolaGui.wk_N; i++) {
		
		String bez_M = "$M_Kaefig"+i+"_WK"+i;
		String bez_K_M 		= "$M_Kaefig" + i;
		
		towrite = towrite +
						"marker.type (                       " + bez_K_M +"                   ) = 2                       ! Type\r\n" + 
						"marker.parent (                     " + bez_K_M +"                   ) = $B_Kaefig               ! Body\r\n" + 
						"marker.pos (              1 ,       " + bez_K_M +"                   ) = { $G_WK_PositionsDaten.$_ZyRoLA_WK" +i+ "_x } ! Position\r\n" + 
						"marker.pos (              2 ,       " + bez_K_M +"                   ) = { $G_WK_PositionsDaten.$_ZyRoLA_WK" +i+ "_y } ! Position\r\n" + 
						"marker.ang (              3 ,       " + bez_K_M +"                   ) = { $G_WK_PositionsDaten.$_ZyRoLA_gamma" +i+" } ! Angles\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"\r\n" + 				
                       "marker.type (                       "+bez_M+"              ) = -82                     ! Type\r\n" + 
                       "marker.parent (                     "+bez_M+"              ) = $B_Kaefig               ! Body\r\n" + 
                       "marker.pos (              1 ,       "+bez_M+"              ) = { $G_WK_PositionsDaten.$_ZyRoLA_WK1_x/1000 } ! Position\r\n" + 
                       "marker.pos (              2 ,       "+bez_M+"              ) = { $G_WK_PositionsDaten.$_ZyRoLA_WK1_y/1000 } ! Position\r\n" + 
                       "marker.ang (              1 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! Angles\r\n" + 
                       "marker.ang (              3 ,       "+bez_M+"              ) = { $G_WK_PositionsDaten.$_ZyRoLA_gamma1 } ! Angles\r\n" + 
                       "marker.par (              1 ,       "+bez_M+"              ) = $M_WK"+i+"                 ! [-] Leading Marker\r\n" + 
                       "marker.par (              2 ,       "+bez_M+"              ) = $M_Kaefig"+i+"             ! [-] Reference Marker\r\n" + 
                       "marker.par (              3 ,       "+bez_M+"              ) = $M_Kaefig               ! [-] not used\r\n" + 
                       "marker.par (              4 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [m] Built-in position x\r\n" + 
                       "marker.par (              5 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [m] Built-in position y\r\n" + 
                       "marker.par (              6 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [m] Built-in position z\r\n" + 
                       "marker.par (              7 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [rad] Built-in orientation al\r\n" + 
                       "marker.par (              8 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [rad] Built-in orientation be\r\n" + 
                       "marker.par (              9 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [rad] Built-in orientation ga\r\n" + 
                       "marker.par (             10 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Lock position in x\r\n" + 
                       "marker.par (             11 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Lock position in y\r\n" + 
                       "marker.par (             12 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Lock position in z\r\n" + 
                       "marker.par (             13 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Lock orientation in al\r\n" + 
                       "marker.par (             14 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Lock orientation in be\r\n" + 
                       "marker.par (             15 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Lock orientation in ga\r\n" + 
                       "marker.par (             16 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Set v_rel to zero"+
						"\r\n" + 
						"\r\n" +
						"\r\n" +"\r\n" + 
						"\r\n" +
						"\r\n";                                 
		
	}
				
				
				
		for (int i=1; i<= ZyrolaGui.wk_N; i++) {
			
			String bez_MK_B	 		= "$B_Kaefig_B"+ i;
			
	
			towrite = towrite + 
					"prim.type (                         "+ bez_MK_B +"                 ) = 1                       ! Type\r\n" + 
					"prim.ref (                          "+ bez_MK_B +"                 ) = $M_Kaefig              ! Reference Marker\r\n" + 
					"prim.pos (                1 ,       "+ bez_MK_B +"                 ) = { (($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_aussen+$G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_innen)/4)*sin(-(360/"+ZyrolaGui.wk_N+")*("+ (i-0.5) +"deg)) } ! Position\r\n" + 
					"prim.pos (                2 ,       "+ bez_MK_B +"                 ) = { (($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_aussen+$G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_innen)/4)*cos(-(360/"+ZyrolaGui.wk_N+")*("+ (i-0.5) +"deg)) } ! Position\r\n" + 
					"prim.ang (                1 ,       "+ bez_MK_B +"                 ) = 0.0000000000000000E+00  ! Angles\r\n" + 
					"prim.ang (                3 ,       "+ bez_MK_B +"                 ) = {(360/"+ZyrolaGui.wk_N+")*("+ (i-0.5) +"deg) } ! Angles\r\n" + 
					"prim.color.r (            1 ,       "+ bez_MK_B +"                 ) = 1.2800000000000000E+02 ! Colors (red component)\r\n" + 
					"prim.color.g (            1 ,       "+ bez_MK_B +"                 ) = 0.0000000000000000E+00 ! Colors (green component)\r\n" + 
					"prim.color.b (            1 ,       "+ bez_MK_B +"                 ) = 0.0000000000000000E+00 ! Colors (blue component)\r\n" + 
					"prim.color.t (            1 ,       "+ bez_MK_B +"                 ) = 0.0000000000000000E+00 ! Colors (transparency component)\r\n" + 
					"prim.mp.dens.solid (                "+ bez_MK_B +"                	) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_rho  ! Density\r\n"+
					"prim.par (                2 ,       "+ bez_MK_B +"                 ) = { $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_DickeSteg } ! [m] Length in X\r\n" + 
					"prim.par (                3 ,       "+ bez_MK_B +"                 ) = {(($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_aussen-$G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_innen)/2)	  }! [m] Length in Y\r\n" + 
					"prim.par (                4 ,       "+ bez_MK_B +"                 ) = {($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_Breite+0.1*$G_GeometrieParameter.$_ZyRoLA_WK_z)}          ! [m] Length in Z"  +
					"\r\n"+
					"\r\n"+
					"\r\n";
	
	
			}
		
	}
	
	
	
	
	//Schleife für Kontaktkäfig
	
	if (ZyrolaGui.kaefigindex == 2) {
		

	
	
	towrite = towrite +
				"body.m (                            $B_Kaefig                    ) = 2.0000000000000001E-01  ! Mass of the Body\r\n" + 
				"body.mp (                           $B_Kaefig                    ) = 1                       ! 0=manual; 1=auto (based on geometry); 2=mass manual, CG & Inertia auto\r\n" + 
				"body.I.tens (             1 ,   1 , $B_Kaefig                    ) = 1.0000000000000000E-03  ! Moments of inertia\r\n" + 
				"body.I.tens (             2 ,   2 , $B_Kaefig                    ) = 1.0000000000000000E-03  ! Moments of inertia\r\n" + 
				"body.I.tens (             3 ,   3 , $B_Kaefig                    ) = 1.0000000000000000E-04  ! Moments of inertia\r\n" + 
				"body.I.kind (                       $B_Kaefig                    ) = -1                      ! Kind of I-tensor specification: -1=wrt CG; 0=wrt BRF; 1=wrt Marker\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Kaefig_BRF                ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Kaefig_BRF                ) = $B_Kaefig              ! Body\r\n" + 
				"marker.flx.type (                   $M_Kaefig_BRF                ) = 4                       ! Flexible type\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Kaefig                    ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Kaefig                    ) = $B_Kaefig              ! Body\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Kaefig_Aussen             ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Kaefig_Aussen             ) = $B_Kaefig              ! Body\r\n" + 
				"marker.pos (              3 ,       $M_Kaefig_Aussen             ) = {-($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_Breite/2)} ! Position\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Kaefig_Ref                ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Kaefig_Ref                ) = $B_Kaefig              ! Body\r\n" + 
				"marker.pos (              3 ,       $M_Kaefig_Ref                ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"\r\n" +
				"\r\n" +
				"\r\n" +
				"joint.from (                        $J_Kaefig                    ) = $M_Innenring            ! From Marker\r\n" + 
				"joint.to (                          $J_Kaefig                    ) = $M_Kaefig              ! To Marker\r\n" + 
				"joint.type (                        $J_Kaefig                    ) = 25                      ! Type\r\n";
	
				if(ZyrolaGui.s_lagermod == 0) towrite = towrite + "joint.st.vel (            1 ,       $J_Kaefig                     ) = 0.0000000000000000E+00  ! Velocity\r\n";
				if(ZyrolaGui.s_lagermod == 1) towrite = towrite + "joint.st.vel (            1 ,       $J_Kaefig                     ) = {$G_Simulationsparameter.$_ZyRoLa_sim_dreh_KF - ($G_Simulationsparameter.$_ZyRoLa_sim_dreh_IR/60*2*3.14)}  ! Velocity\r\n";						
		towrite = towrite +
				"joint.par (               1 ,       $J_Kaefig                    ) = 1.0000000000000000E+00  ! [-] Axis of Rotation    1\r\n" + 
				"joint.par (               2 ,       $J_Kaefig                    ) = 2.0000000000000000E+00  ! [-] Axis of Rotation    2\r\n" + 
				"joint.par (               3 ,       $J_Kaefig                    ) = 3.0000000000000000E+00  ! [-] Axis of Rotation    3\r\n" + 
				"joint.par (               4 ,       $J_Kaefig                    ) = 1.0000000000000000E+00  ! [-] Axis of Translation 1\r\n" + 
				"joint.par (               5 ,       $J_Kaefig                    ) = 2.0000000000000000E+00  ! [-] Axis of Translation 2\r\n" + 
				"joint.par (               6 ,       $J_Kaefig                    ) = 3.0000000000000000E+00  ! [-] Axis of Translation 3\r\n" + 
				"joint.par (               8 ,       $J_Kaefig                    ) = 0.0000000000000000E+00  ! [-] Trans-Rot Sequence:"+
				"\r\n" + 
				"\r\n" + 
				"prim.type (                         $P_Kaefig                    ) = 2                       ! Type\r\n" + 
				"prim.ref (                          $P_Kaefig                    ) = $M_Kaefig              ! Reference Marker\r\n" + 
				"prim.pos (                3 ,       $P_Kaefig                    ) = { (($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_Breite/2+0.1*$G_GeometrieParameter.$_ZyRoLA_WK_z))} ! Position\r\n" + 
				"prim.ang (                1 ,       $P_Kaefig                    ) = $G_sonstigeParameter.$_ZyRoLA_alpha          ! Angles\r\n" + 
				"prim.color.r (            1 ,       $P_Kaefig                    ) = 1.2800000000000000E+02  ! Colors (red component)\r\n" + 
				"prim.color.r (            2 ,       $P_Kaefig                    ) = 1.2800000000000000E+02  ! Colors (red component)\r\n" + 
				"prim.color.g (            1 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! Colors (green component)\r\n" + 
				"prim.color.g (            2 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! Colors (green component)\r\n" + 
				"prim.color.b (            1 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! Colors (blue component)\r\n" + 
				"prim.color.b (            2 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! Colors (blue component)\r\n" + 
				"prim.color.t (            1 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.color.t (            2 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.mp.dens.solid (                $P_Kaefig                		) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_rho  ! Density\r\n"+
				"prim.par (                2 ,       $P_Kaefig                    ) = {(0.1*$G_GeometrieParameter.$_ZyRoLA_WK_z) }         ! [m] Height\r\n" + 
				"prim.par (                3 ,       $P_Kaefig                    ) = { ($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_aussen) } ! [m] Outer diameter\r\n" + 
				"prim.par (                4 ,       $P_Kaefig                    ) = { ($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_innen) } ! [m] Inner diameter\r\n" + 
				"prim.par (                5 ,       $P_Kaefig                    ) = 4.8000000000000000E+01  ! [-] Number of planes\r\n" + 
				"prim.par (                6 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! [-] Number of highlighted planes\r\n" + 
				"prim.par (                7 ,       $P_Kaefig                    ) = 1.0000000000000000E+00  ! [-] Show bottom cap\r\n" + 
				"prim.par (                8 ,       $P_Kaefig                    ) = 1.0000000000000000E+00  ! [-] Show top cap\r\n" + 
				"prim.par (                9 ,       $P_Kaefig                    ) = 0.0000000000000000E+00  ! [rad] Start angle\r\n" + 
				"\r\n" + 
				"prim.type (                         $P_KaefigR2                  ) = 2                       ! Type\r\n" + 
				"prim.ref (                          $P_KaefigR2                  ) = $M_Kaefig              ! Reference Marker\r\n" + 
				"prim.pos (                3 ,       $P_KaefigR2                  ) = { -(($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_Breite/2+0.1*$G_GeometrieParameter.$_ZyRoLA_WK_z)) } ! Position\r\n" + 
				"prim.ang (                1 ,       $P_KaefigR2                  ) = $G_sonstigeParameter.$_ZyRoLA_alpha          ! Angles\r\n" + 
				"prim.color.r (            1 ,       $P_KaefigR2                  ) = 1.2800000000000000E+02  ! Colors (red component)\r\n" + 
				"prim.color.r (            2 ,       $P_KaefigR2                  ) = 1.2800000000000000E+02  ! Colors (red component)\r\n" + 
				"prim.color.g (            1 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! Colors (green component)\r\n" + 
				"prim.color.g (            2 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! Colors (green component)\r\n" + 
				"prim.color.b (            1 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! Colors (blue component)\r\n" + 
				"prim.color.b (            2 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! Colors (blue component)\r\n" + 
				"prim.color.t (            1 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.color.t (            2 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.mp.dens.solid (                $P_KaefigR2                	) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_rho  ! Density\r\n"+
				"prim.par (                2 ,       $P_KaefigR2                  ) = {(0.1*$G_GeometrieParameter.$_ZyRoLA_WK_z)}           ! [m] Height\r\n" + 
				"prim.par (                3 ,       $P_KaefigR2                  ) = { ($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_aussen) } ! [m] Outer diameter\r\n" + 
				"prim.par (                4 ,       $P_KaefigR2                  ) = { ($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_innen) } ! [m] Inner diameter\r\n" + 
				"prim.par (                5 ,       $P_KaefigR2                  ) = 4.8000000000000000E+01  ! [-] Number of planes\r\n" + 
				"prim.par (                6 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! [-] Number of highlighted planes\r\n" + 
				"prim.par (                7 ,       $P_KaefigR2                  ) = 1.0000000000000000E+00  ! [-] Show bottom cap\r\n" + 
				"prim.par (                8 ,       $P_KaefigR2                  ) = 1.0000000000000000E+00  ! [-] Show top cap\r\n" + 
				"prim.par (                9 ,       $P_KaefigR2                  ) = 0.0000000000000000E+00  ! [rad] Start angle\r\n" + 
				"\r\n"+
				"\r\n"+
				"\r\n";
	
	
	for (int i=1; i <= ZyrolaGui.wk_N; i++) {
		
		String bez_M = "$M_Kaefig"+i+"_WK"+i;
		String bez_K_M 		= "$M_Kaefig" + i;
		
		towrite = towrite +
						"marker.type (                       " + bez_K_M +"                   ) = 2                       ! Type\r\n" + 
						"marker.parent (                     " + bez_K_M +"                   ) = $B_Kaefig               ! Body\r\n" + 
						"marker.pos (              1 ,       " + bez_K_M +"                   ) = { $G_WK_PositionsDaten.$_ZyRoLA_WK" +i+ "_x } ! Position\r\n" + 
						"marker.pos (              2 ,       " + bez_K_M +"                   ) = { $G_WK_PositionsDaten.$_ZyRoLA_WK" +i+ "_y } ! Position\r\n" + 
						"marker.ang (              3 ,       " + bez_K_M +"                   ) = { $G_WK_PositionsDaten.$_ZyRoLA_gamma" +i+" } ! Angles\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"\r\n" + 				
                       "marker.type (                       "+bez_M+"              ) = -82                     ! Type\r\n" + 
                       "marker.parent (                     "+bez_M+"              ) = $B_Kaefig               ! Body\r\n" + 
                       "marker.pos (              1 ,       "+bez_M+"              ) = { $G_WK_PositionsDaten.$_ZyRoLA_WK1_x/1000 } ! Position\r\n" + 
                       "marker.pos (              2 ,       "+bez_M+"              ) = { $G_WK_PositionsDaten.$_ZyRoLA_WK1_y/1000 } ! Position\r\n" + 
                       "marker.ang (              1 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! Angles\r\n" + 
                       "marker.ang (              3 ,       "+bez_M+"              ) = { $G_WK_PositionsDaten.$_ZyRoLA_gamma1 } ! Angles\r\n" + 
                       "marker.par (              1 ,       "+bez_M+"              ) = $M_WK"+i+"                 ! [-] Leading Marker\r\n" + 
                       "marker.par (              2 ,       "+bez_M+"              ) = $M_Kaefig"+i+"             ! [-] Reference Marker\r\n" + 
                       "marker.par (              3 ,       "+bez_M+"              ) = $M_Kaefig               ! [-] not used\r\n" + 
                       "marker.par (              4 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [m] Built-in position x\r\n" + 
                       "marker.par (              5 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [m] Built-in position y\r\n" + 
                       "marker.par (              6 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [m] Built-in position z\r\n" + 
                       "marker.par (              7 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [rad] Built-in orientation al\r\n" + 
                       "marker.par (              8 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [rad] Built-in orientation be\r\n" + 
                       "marker.par (              9 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [rad] Built-in orientation ga\r\n" + 
                       "marker.par (             10 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Lock position in x\r\n" + 
                       "marker.par (             11 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Lock position in y\r\n" + 
                       "marker.par (             12 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Lock position in z\r\n" + 
                       "marker.par (             13 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Lock orientation in al\r\n" + 
                       "marker.par (             14 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Lock orientation in be\r\n" + 
                       "marker.par (             15 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Lock orientation in ga\r\n" + 
                       "marker.par (             16 ,       "+bez_M+"              ) = 0.0000000000000000E+00  ! [-] Set v_rel to zero"+
						"\r\n" + 
						"\r\n" +
						"\r\n" +"\r\n" + 
						"\r\n" +
						"\r\n";                                 
		
	}
				
				
				
		for (int i=1; i<= ZyrolaGui.wk_N; i++) {
			
			String bez_MK_B	 		= "$B_Kaefig_B"+ i;
			
			towrite = towrite + 
					"prim.type (                         "+ bez_MK_B +"                 ) = 1                       ! Type\r\n" + 
					"prim.ref (                          "+ bez_MK_B +"                 ) = $M_Kaefig              ! Reference Marker\r\n" + 
					"prim.pos (                1 ,       "+ bez_MK_B +"                 ) = { (($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_aussen+$G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_innen)/4)*sin(-(360/"+ZyrolaGui.wk_N+")*("+ (i-0.5) +"deg)) } ! Position\r\n" + 
					"prim.pos (                2 ,       "+ bez_MK_B +"                 ) = { (($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_aussen+$G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_innen)/4)*cos(-(360/"+ZyrolaGui.wk_N+")*("+ (i-0.5) +"deg)) } ! Position\r\n" + 
					"prim.ang (                1 ,       "+ bez_MK_B +"                 ) = 0.0000000000000000E+00  ! Angles\r\n" + 
					"prim.ang (                3 ,       "+ bez_MK_B +"                 ) = {(360/"+ZyrolaGui.wk_N+")*("+ (i-0.5) +"deg) } ! Angles\r\n" + 
					"prim.color.r (            1 ,       "+ bez_MK_B +"                 ) = 1.2800000000000000E+02  ! Colors (red component)\r\n" + 
					"prim.color.g (            1 ,       "+ bez_MK_B +"                 ) = 0.0000000000000000E+00  ! Colors (green component)\r\n" + 
					"prim.color.b (            1 ,       "+ bez_MK_B +"                 ) = 0.0000000000000000E+00  ! Colors (blue component)\r\n" + 
					"prim.color.t (            1 ,       "+ bez_MK_B +"                 ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
					"prim.mp.dens.solid (                "+ bez_MK_B +"                	) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_rho  ! Density\r\n"+
					"prim.par (                2 ,       "+ bez_MK_B +"                 ) = { $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_DickeSteg } ! [m] Length in X\r\n" + 
					"prim.par (                3 ,       "+ bez_MK_B +"                 ) = {(($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_aussen-$G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_innen)/2)		  }! [m] Length in Y\r\n" + 
					"prim.par (                4 ,       "+ bez_MK_B +"                 ) = {($G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_Breite+0.1*$G_GeometrieParameter.$_ZyRoLA_WK_z)}          ! [m] Length in Z"  +
					"\r\n"+
					"\r\n"+
					"\r\n";
	
	
			}
	}			
				
	
	
	// Schleife für Waelzkörper
	

	
	for (int i=1; i<= ZyrolaGui.wk_N; i++) {
		
		String bez_WK_B	 		= "$B_WK"+ i;
		String bez_WK_M_Brf 	= "$M_WK"+ i + "_BRF";
		String bez_WK_M 		= "$M_WK"+ i;
		String bez_WK_M_a		= "$M_WK"+ i +"_aussen";
		String bez_WK_P 		= "$P_WK"+ i + "_cubiod";
		String bez_WK_J 		= "$J_WK"+ i;
		String bez_WK_A 		= "$M_Aussenring_WK"+ i;
		String bez_WK_I			= "$M_Innenring_WK" + i;
		
		
	
	
		towrite = towrite +

				"\r\n" +
				"body.m (                           " + bez_WK_B + "                        ) = 1.0000000000000000E+00  ! Mass of the Body\r\n" + 
				"body.mp (                          " + bez_WK_B + "                        ) = 1                       ! 0=manual; 1=auto (based on geometry); 2=mass manual, CG & Inertia auto\r\n" + 
				"body.I.tens (             1 ,   1 ," + bez_WK_B + "                        ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
				"body.I.tens (             2 ,   2 ," + bez_WK_B + "                        ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
				"body.I.tens (             3 ,   3 ," + bez_WK_B + "                        ) = 1.0000000000000000E+00  ! Moments of inertia\r\n" + 
				"body.attr.2d.pos.x (               " + bez_WK_B + "                        ) = -3.0000000000000000E+01\r\n" + 
				"body.attr.2d.pos.y (               " + bez_WK_B + "                        ) = 2.3000000000000000E+02\r\n" + 
				"body.attr.2d.width (               " + bez_WK_B + "                        ) = 5.0000000000000000E+01\r\n" + 
				"body.attr.2d.height (              " + bez_WK_B + "                        ) = 3.0000000000000000E+01\r\n" + 
				"\r\n" + 
				"marker.type (                       " + bez_WK_M_Brf + "                    ) = 1                       ! Type\r\n" + 
				"marker.parent (                     " + bez_WK_M_Brf + "                    ) = " + bez_WK_B + "                  ! Body\r\n" + 
				"marker.flx.type (                   " + bez_WK_M_Brf + "                    ) = 4                       ! Flexible type\r\n" + 
				"\r\n" + 
				"marker.type (                       " + bez_WK_M + "                        ) = 1                       ! Type\r\n" + 
				"marker.parent (                     " + bez_WK_M + "                        ) = " + bez_WK_B + "                  ! Body\r\n" + 
				"\r\n" + 
				"marker.type (                       " + bez_WK_M_a + "                 ) = 1                       ! Type\r\n" + 
				"marker.ref (                        " + bez_WK_M_a + "                 ) = " + bez_WK_M + "                  ! Reference Marker\r\n" + 
				"marker.parent (                     " + bez_WK_M_a + "                 ) = " + bez_WK_B + "                  ! Body\r\n" + 
				"marker.pos (              2 ,       " + bez_WK_M_a + "                 ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"marker.pos (              3 ,       " + bez_WK_M_a + "                 ) = -"+ ZyrolaGui.wk_z/2/1000 +" ! Position\r\n" + 
				"\r\n" + 
				"joint.from (                        " + bez_WK_J +"                        ) = $M_Kaefig" +i+ "              ! From Marker\r\n" + 
				"joint.to (                          " + bez_WK_J +"                        ) = " + bez_WK_M + "                 ! To Marker\r\n" + 
				"joint.type (                        " + bez_WK_J +"                        ) = 25                      ! Type\r\n" + 
				"joint.st.pos (            1 ,       " + bez_WK_J +"                        ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"joint.st.pos (            2 ,       " + bez_WK_J +"                        ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"joint.st.pos (            3 ,       " + bez_WK_J +"                        ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"joint.st.pos (            4 ,       " + bez_WK_J +"                        ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"joint.st.pos (            5 ,       " + bez_WK_J +"                        ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"joint.st.pos (            6 ,       " + bez_WK_J +"                        ) = 0.0000000000000000E+00  ! Position\r\n"; 
				if(ZyrolaGui.s_lagermod == 0) towrite = towrite + "joint.st.vel (            1 ,       " + bez_WK_J +"                     ) = 0.0000000000000000E+00  ! Velocity\r\n";
				if(ZyrolaGui.s_lagermod == 1) towrite = towrite + "joint.st.vel (            "+ZyrolaGui.s_kippen_index+" ,       " + bez_WK_J +"                     ) = {$G_Simulationsparameter.$_ZyRoLa_sim_dreh_WK}  ! Velocity\r\n";				
		towrite = towrite +
				"joint.st.dep (            1 ,       " + bez_WK_J +"                        ) = 0                       ! Dependency state\r\n" + 
				"joint.st.equi (           2 ,       " + bez_WK_J +"                        ) = 0                       ! Equilibrium\r\n" + 
				"joint.par (               1 ,       " + bez_WK_J +"                        ) = "+ ZyrolaGui.wk_joint_def_rotA+"   ! [-] Axis of Rotation    1\r\n" + 
				"joint.par (               2 ,       " + bez_WK_J +"                        ) = "+ ZyrolaGui.wk_joint_def_rotB+"  ! [-] Axis of Rotation    2\r\n" + 
				"joint.par (               3 ,       " + bez_WK_J +"                        ) = "+ ZyrolaGui.wk_joint_def_rotC+"  ! [-] Axis of Rotation    3\r\n" + 
				"joint.par (               4 ,       " + bez_WK_J +"                        ) = "+ ZyrolaGui.wk_joint_def_transA +"  ! [-] Axis of Translation 1\r\n" + 
				"joint.par (               5 ,       " + bez_WK_J +"                        ) = "+ ZyrolaGui.wk_joint_def_transB +" ! [-] Axis of Translation 2\r\n" + 
				"joint.par (               6 ,       " + bez_WK_J +"                        ) = "+ ZyrolaGui.wk_joint_def_transC +" ! [-] Axis of Translation 3\r\n" + 
				"joint.par (               8 ,       " + bez_WK_J +"                        ) = 0.0000000000000000E+00  ! [-] Trans-Rot Sequence:\r\n" + 

				"\r\n" + 
				"prim.type (                         " + bez_WK_P + "                 ) = 2                       ! Type\r\n" + 
				"prim.ref (                          " + bez_WK_P + "                 ) = " + bez_WK_M_Brf + "              ! Reference Marker\r\n" + 
				"prim.pos (                1 ,       " + bez_WK_P + "                 ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"prim.pos (                2 ,       " + bez_WK_P + "                 ) = 0.0000000000000000E+00  ! Position\r\n" + 
				"prim.pos (                3 ,       " + bez_WK_P + "                 ) = 0.0000000000000000E+00  ! Position\r\n" +
				"prim.ang (                1 ,       " + bez_WK_P + "                 ) = { $G_sonstigeParameter.$_ZyRoLA_alpha } ! Angles\r\n"+
				"prim.color.r (            1 ,       " + bez_WK_P + "                 ) = 6.4000000000000000E+01  ! Colors (red component)\r\n" + 
				"prim.color.r (            2 ,       " + bez_WK_P + "                 ) = 6.4000000000000000E+01  ! Colors (red component)\r\n" + 
				"prim.color.g (            1 ,       " + bez_WK_P + "                 ) = 6.4000000000000000E+01  ! Colors (green component)\r\n" + 
				"prim.color.g (            2 ,       " + bez_WK_P + "                 ) = 6.4000000000000000E+01  ! Colors (green component)\r\n" + 
				"prim.color.b (            1 ,       " + bez_WK_P + "                 ) = 6.4000000000000000E+01  ! Colors (blue component)\r\n" + 
				"prim.color.b (            2 ,       " + bez_WK_P + "                 ) = 6.4000000000000000E+01  ! Colors (blue component)\r\n" + 
				"prim.color.t (            1 ,       " + bez_WK_P + "                 ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.color.t (            2 ,       " + bez_WK_P + "                 ) = 0.0000000000000000E+00  ! Colors (transparency component)\r\n" + 
				"prim.shading (                      " + bez_WK_P + "                 ) = 2                       ! Shading\r\n" + 
				"prim.drawstyle (                    " + bez_WK_P + "                 ) = 2                       ! Draw style\r\n" + 
				"prim.transparency (                 " + bez_WK_P + "                 ) = 2.5000000000000000E-01  ! Transparency\r\n"+
				"prim.mp.dens.solid (                " + bez_WK_P + "                 ) = {$G_GeometrieMaterialdaten.$_ZyRoLA_WK_rho}  ! Density\r\n" + 
				"prim.par (                2 ,       " + bez_WK_P + "                 ) = { $G_GeometrieParameter.$_ZyRoLA_WK_z } 		! [m] Height\r\n" + 
				"prim.par (                3 ,       " + bez_WK_P + "                 ) = { $G_GeometrieParameter.$_ZyRoLA_WK_D } 		! [m] Outer diameter\r\n" + 
				"prim.par (                4 ,       " + bez_WK_P + "                 ) = 0.0000000000000000E+00  ! [m] Inner diameter\r\n" + 
				"prim.par (                5 ,       " + bez_WK_P + "                 ) = 9.6000000000000000E+01  ! [-] Number of planes\r\n" + 
				"prim.par (                6 ,       " + bez_WK_P + "                 ) = 0.0000000000000000E+00  ! [-] Number of highlighted planes\r\n" + 
				"prim.par (                7 ,       " + bez_WK_P + "                 ) = 1.0000000000000000E+00  ! [-] Show bottom cap\r\n" + 
				"prim.par (                8 ,       " + bez_WK_P + "                 ) = 1.0000000000000000E+00  ! [-] Show top cap\r\n" + 
				"prim.par (                9 ,       " + bez_WK_P + "                 ) = 0.0000000000000000E+00  ! [rad] Start angle\r\n" + 
				"prim.par (               10 ,       " + bez_WK_P + "                 ) = 0.0000000000000000E+00  ! [rad] Delta angle\r\n" + 
				"prim.par (               11 ,       " + bez_WK_P + "                 ) = 0.0000000000000000E+00  ! [-] Close geometry with" +

				"\r\n" + 
				"\r\n" + // Ref Marker zum   Außenring
				"marker.type (                       " + bez_WK_A + "               ) = -82                     ! Type\r\n" + 
				"marker.parent (                     " + bez_WK_A + "               ) = $B_Aussenring            ! Body\r\n" + 
				"marker.par (              1 ,       " + bez_WK_A + "               ) = " + bez_WK_M + "           ! [-] Leading Marker\r\n" + 
				"marker.par (              2 ,       " + bez_WK_A + "               ) = $M_Aussenring_Ref        ! [-] Reference Marker\r\n" + 
				"marker.par (              3 ,       " + bez_WK_A + "               ) = $M_Aussenring            ! [-] not used\r\n" + 
				"marker.par (              4 ,       " + bez_WK_A + "               ) = 0.0000000000000000E+00  ! [m] Built-in position x\r\n" + 
				"marker.par (              5 ,       " + bez_WK_A + "               ) = 0.0000000000000000E+00  ! [m] Built-in position y\r\n" + 
				"marker.par (              6 ,       " + bez_WK_A + "               ) = 0.0000000000000000E+00  ! [m] Built-in position z\r\n" + 
				"marker.par (              7 ,       " + bez_WK_A + "               ) = 0.0000000000000000E+00  ! [rad] Built-in orientation al\r\n" + 
				"marker.par (              8 ,       " + bez_WK_A + "               ) = 0.0000000000000000E+00  ! [rad] Built-in orientation be\r\n" + 
				"marker.par (              9 ,       " + bez_WK_A + "               ) = 0.0000000000000000E+00  ! [rad] Built-in orientation ga\r\n" + 
				"marker.par (             10 ,       " + bez_WK_A + "               ) = 0.0000000000000000E+00  ! [-] Lock position in x\r\n" + 
				"marker.par (             11 ,       " + bez_WK_A + "               ) = 0.0000000000000000E+00  ! [-] Lock position in y\r\n" + 
				"marker.par (             12 ,       " + bez_WK_A + "               ) = 0.0000000000000000E+00  ! [-] Lock position in z\r\n" + 
				"marker.par (             13 ,       " + bez_WK_A + "               ) = 1.0000000000000000E+00  ! [-] Lock orientation in al\r\n" + 
				"marker.par (             14 ,       " + bez_WK_A + "               ) = 1.0000000000000000E+00  ! [-] Lock orientation in be\r\n" + 
				"marker.par (             15 ,       " + bez_WK_A + "               ) = 1.0000000000000000E+00  ! [-] Lock orientation in ga\r\n" + 
				"marker.par (             16 ,       " + bez_WK_A + "               ) = 0.0000000000000000E+00  ! [-] Set v_rel to zero\r\n" +
				"\r\n" +
				"\r\n" +
				"marker.type (                       " + bez_WK_I + "               ) = -82                     ! Type\r\n" + 
				"marker.parent (                     " + bez_WK_I + "               ) = $B_Innenring            ! Body\r\n" + 
				"marker.par (              1 ,       " + bez_WK_I + "               ) = " + bez_WK_M + "           ! [-] Leading Marker\r\n" + 
				"marker.par (              2 ,       " + bez_WK_I + "               ) = $M_Innenring_Ref        ! [-] Reference Marker\r\n" + 
				"marker.par (              3 ,       " + bez_WK_I + "               ) = $M_Innenring            ! [-] not used\r\n" + 
				"marker.par (              4 ,       " + bez_WK_I + "               ) = 0.0000000000000000E+00  ! [m] Built-in position x\r\n" + 
				"marker.par (              5 ,       " + bez_WK_I + "               ) = 0.0000000000000000E+00  ! [m] Built-in position y\r\n" + 
				"marker.par (              6 ,       " + bez_WK_I + "               ) = 0.0000000000000000E+00  ! [m] Built-in position z\r\n" + 
				"marker.par (              7 ,       " + bez_WK_I + "               ) = 0.0000000000000000E+00  ! [rad] Built-in orientation al\r\n" + 
				"marker.par (              8 ,       " + bez_WK_I + "               ) = 0.0000000000000000E+00  ! [rad] Built-in orientation be\r\n" + 
				"marker.par (              9 ,       " + bez_WK_I + "               ) = 0.0000000000000000E+00  ! [rad] Built-in orientation ga\r\n" + 
				"marker.par (             10 ,       " + bez_WK_I + "               ) = 0.0000000000000000E+00  ! [-] Lock position in x\r\n" + 
				"marker.par (             11 ,       " + bez_WK_I + "               ) = 0.0000000000000000E+00  ! [-] Lock position in y\r\n" + 
				"marker.par (             12 ,       " + bez_WK_I + "               ) = 0.0000000000000000E+00  ! [-] Lock position in z\r\n" + 
				"marker.par (             13 ,       " + bez_WK_I + "               ) = 1.0000000000000000E+00  ! [-] Lock orientation in al\r\n" + 
				"marker.par (             14 ,       " + bez_WK_I + "               ) = 1.0000000000000000E+00  ! [-] Lock orientation in be\r\n" + 
				"marker.par (             15 ,       " + bez_WK_I + "               ) = 1.0000000000000000E+00  ! [-] Lock orientation in ga\r\n" + 
				"marker.par (             16 ,       " + bez_WK_I + "               ) = 0.0000000000000000E+00  ! [-] Set v_rel to zero\r\n" +
				"\r\n"+
				"\r\n"+
				"\r\n";
	}
	
	//Marker für Bordkontakt
	
	
	if (ZyrolaGui.mkaefigindex != 0) {
		
		
		
	towrite = towrite + 
				"marker.type (                       $M_Aussenring_Bord1           ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Aussenring_Bord1           ) = $B_Aussenring           ! Body\r\n" + 
				"marker.pos (              3 ,       $M_Aussenring_Bord1           ) = {("+ var[0] +"-"+var[1]+")/2}  ! Position\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Aussenring_Bord2           ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Aussenring_Bord2           ) = $B_Aussenring           ! Body\r\n" + 
				"marker.pos (              3 ,       $M_Aussenring_Bord2           ) = {-("+ var[0] +"-"+var[1]+")/2} ! Position\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Aussenring_Bord1j          ) = -82                     ! Type\r\n" + 
				"marker.parent (                     $M_Aussenring_Bord1j          ) = $B_Aussenring           ! Body\r\n" + 
				"marker.par (              1 ,       $M_Aussenring_Bord1j          ) = $M_MKaefig_Bord1        ! [-] Leading Marker\r\n" + 
				"marker.par (              2 ,       $M_Aussenring_Bord1j          ) = $M_Aussenring_Bord1     ! [-] Reference Marker\r\n" + 
				"marker.par (              3 ,       $M_Aussenring_Bord1j          ) = $M_Aussenring           ! [-] not used\r\n" + 
				"marker.par (              4 ,       $M_Aussenring_Bord1j          ) = 0.0000000000000000E+00  ! [m] Built-in position x\r\n" + 
				"marker.par (              5 ,       $M_Aussenring_Bord1j          ) = 0.0000000000000000E+00  ! [m] Built-in position y\r\n" + 
				"marker.par (              6 ,       $M_Aussenring_Bord1j          ) = 0.0000000000000000E+00  ! [m] Built-in position z\r\n" + 
				"marker.par (              7 ,       $M_Aussenring_Bord1j          ) = 0.0000000000000000E+00  ! [rad] Built-in orientation al\r\n" + 
				"marker.par (              8 ,       $M_Aussenring_Bord1j          ) = 0.0000000000000000E+00  ! [rad] Built-in orientation be\r\n" + 
				"marker.par (              9 ,       $M_Aussenring_Bord1j          ) = 0.0000000000000000E+00  ! [rad] Built-in orientation ga\r\n" + 
				"marker.par (             10 ,       $M_Aussenring_Bord1j          ) = 0.0000000000000000E+00  ! [-] Lock position in x\r\n" + 
				"marker.par (             11 ,       $M_Aussenring_Bord1j          ) = 0.0000000000000000E+00  ! [-] Lock position in y\r\n" + 
				"marker.par (             12 ,       $M_Aussenring_Bord1j          ) = 0.0000000000000000E+00  ! [-] Lock position in z\r\n" + 
				"marker.par (             13 ,       $M_Aussenring_Bord1j          ) = 1.0000000000000000E+00  ! [-] Lock orientation in al\r\n" + 
				"marker.par (             14 ,       $M_Aussenring_Bord1j          ) = 1.0000000000000000E+00  ! [-] Lock orientation in be\r\n" + 
				"marker.par (             15 ,       $M_Aussenring_Bord1j          ) = 1.0000000000000000E+00  ! [-] Lock orientation in ga\r\n" + 
				"marker.par (             16 ,       $M_Aussenring_Bord1j          ) = 0.0000000000000000E+00  ! [-] Set v_rel to zero\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Aussenring_Bord2j          ) = -82                     ! Type\r\n" + 
				"marker.parent (                     $M_Aussenring_Bord2j          ) = $B_Aussenring           ! Body\r\n" + 
				"marker.par (              1 ,       $M_Aussenring_Bord2j          ) = $M_MKaefig_Bord2        ! [-] Leading Marker\r\n" + 
				"marker.par (              2 ,       $M_Aussenring_Bord2j          ) = $M_Aussenring_Bord2     ! [-] Reference Marker\r\n" + 
				"marker.par (              3 ,       $M_Aussenring_Bord2j          ) = $M_Aussenring           ! [-] not used\r\n" + 
				"marker.par (              4 ,       $M_Aussenring_Bord2j          ) = 0.0000000000000000E+00  ! [m] Built-in position x\r\n" + 
				"marker.par (              5 ,       $M_Aussenring_Bord2j          ) = 0.0000000000000000E+00  ! [m] Built-in position y\r\n" + 
				"marker.par (              6 ,       $M_Aussenring_Bord2j          ) = 0.0000000000000000E+00  ! [m] Built-in position z\r\n" + 
				"marker.par (              7 ,       $M_Aussenring_Bord2j          ) = 0.0000000000000000E+00  ! [rad] Built-in orientation al\r\n" + 
				"marker.par (              8 ,       $M_Aussenring_Bord2j          ) = 0.0000000000000000E+00  ! [rad] Built-in orientation be\r\n" + 
				"marker.par (              9 ,       $M_Aussenring_Bord2j          ) = 0.0000000000000000E+00  ! [rad] Built-in orientation ga\r\n" + 
				"marker.par (             10 ,       $M_Aussenring_Bord2j          ) = 0.0000000000000000E+00  ! [-] Lock position in x\r\n" + 
				"marker.par (             11 ,       $M_Aussenring_Bord2j          ) = 0.0000000000000000E+00  ! [-] Lock position in y\r\n" + 
				"marker.par (             12 ,       $M_Aussenring_Bord2j          ) = 0.0000000000000000E+00  ! [-] Lock position in z\r\n" + 
				"marker.par (             13 ,       $M_Aussenring_Bord2j          ) = 1.0000000000000000E+00  ! [-] Lock orientation in al\r\n" + 
				"marker.par (             14 ,       $M_Aussenring_Bord2j          ) = 1.0000000000000000E+00  ! [-] Lock orientation in be\r\n" + 
				"marker.par (             15 ,       $M_Aussenring_Bord2j          ) = 1.0000000000000000E+00  ! [-] Lock orientation in ga\r\n" + 
				"marker.par (             16 ,       $M_Aussenring_Bord2j          ) = 0.0000000000000000E+00  ! [-] Set v_rel to zero"+
				"\r\n" +
				"\r\n" +
				"\r\n" +
				"marker.type (                       $M_Innenring_Bord1            ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Innenring_Bord1            ) = $B_Innenring            ! Body\r\n" + 
				"marker.pos (              3 ,       $M_Innenring_Bord1            ) = {("+ var[4] +"-"+var[5]+")/2}  ! Position\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Innenring_Bord2            ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_Innenring_Bord2            ) = $B_Innenring            ! Body\r\n" + 
				"marker.pos (              3 ,       $M_Innenring_Bord2            ) = {-("+ var[4] +"-"+var[5]+")/2} ! Position\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Innenring_Bord1j           ) = -82                     ! Type\r\n" + 
				"marker.parent (                     $M_Innenring_Bord1j           ) = $B_Innenring            ! Body\r\n" +  
				"marker.par (              1 ,       $M_Innenring_Bord1j           ) = $M_MKaefig_Bord1        ! [-] Leading Marker\r\n" + 
				"marker.par (              2 ,       $M_Innenring_Bord1j           ) = $M_Innenring_Bord1      ! [-] Reference Marker\r\n" + 
				"marker.par (              3 ,       $M_Innenring_Bord1j           ) = $M_Innenring            ! [-] not used\r\n" + 
				"marker.par (              4 ,       $M_Innenring_Bord1j           ) = 0.0000000000000000E+00  ! [m] Built-in position x\r\n" + 
				"marker.par (              5 ,       $M_Innenring_Bord1j           ) = 0.0000000000000000E+00  ! [m] Built-in position y\r\n" + 
				"marker.par (              6 ,       $M_Innenring_Bord1j           ) = 0.0000000000000000E+00  ! [m] Built-in position z\r\n" + 
				"marker.par (              7 ,       $M_Innenring_Bord1j           ) = 0.0000000000000000E+00  ! [rad] Built-in orientation al\r\n" + 
				"marker.par (              8 ,       $M_Innenring_Bord1j           ) = 0.0000000000000000E+00  ! [rad] Built-in orientation be\r\n" + 
				"marker.par (              9 ,       $M_Innenring_Bord1j           ) = 0.0000000000000000E+00  ! [rad] Built-in orientation ga\r\n" + 
				"marker.par (             10 ,       $M_Innenring_Bord1j           ) = 0.0000000000000000E+00  ! [-] Lock position in x\r\n" + 
				"marker.par (             11 ,       $M_Innenring_Bord1j           ) = 0.0000000000000000E+00  ! [-] Lock position in y\r\n" + 
				"marker.par (             12 ,       $M_Innenring_Bord1j           ) = 0.0000000000000000E+00  ! [-] Lock position in z\r\n" + 
				"marker.par (             13 ,       $M_Innenring_Bord1j           ) = 0.0000000000000000E+00  ! [-] Lock orientation in al\r\n" + 
				"marker.par (             14 ,       $M_Innenring_Bord1j           ) = 0.0000000000000000E+00  ! [-] Lock orientation in be\r\n" + 
				"marker.par (             15 ,       $M_Innenring_Bord1j           ) = 0.0000000000000000E+00  ! [-] Lock orientation in ga\r\n" + 
				"marker.par (             16 ,       $M_Innenring_Bord1j           ) = 0.0000000000000000E+00  ! [-] Set v_rel to zero\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_Innenring_Bord2j           ) = -82                     ! Type\r\n" + 
				"marker.parent (                     $M_Innenring_Bord2j           ) = $B_Innenring            ! Body\r\n" + 
				"marker.par (              1 ,       $M_Innenring_Bord2j           ) = $M_MKaefig_Bord2        ! [-] Leading Marker\r\n" + 
				"marker.par (              2 ,       $M_Innenring_Bord2j           ) = $M_Innenring_Bord2      ! [-] Reference Marker\r\n" + 
				"marker.par (              3 ,       $M_Innenring_Bord2j           ) = $M_Innenring            ! [-] not used\r\n" + 
				"marker.par (              4 ,       $M_Innenring_Bord2j           ) = 0.0000000000000000E+00  ! [m] Built-in position x\r\n" + 
				"marker.par (              5 ,       $M_Innenring_Bord2j           ) = 0.0000000000000000E+00  ! [m] Built-in position y\r\n" + 
				"marker.par (              6 ,       $M_Innenring_Bord2j           ) = 0.0000000000000000E+00  ! [m] Built-in position z\r\n" + 
				"marker.par (              7 ,       $M_Innenring_Bord2j           ) = 0.0000000000000000E+00  ! [rad] Built-in orientation al\r\n" + 
				"marker.par (              8 ,       $M_Innenring_Bord2j           ) = 0.0000000000000000E+00  ! [rad] Built-in orientation be\r\n" + 
				"marker.par (              9 ,       $M_Innenring_Bord2j           ) = 0.0000000000000000E+00  ! [rad] Built-in orientation ga\r\n" + 
				"marker.par (             10 ,       $M_Innenring_Bord2j           ) = 0.0000000000000000E+00  ! [-] Lock position in x\r\n" + 
				"marker.par (             11 ,       $M_Innenring_Bord2j           ) = 0.0000000000000000E+00  ! [-] Lock position in y\r\n" + 
				"marker.par (             12 ,       $M_Innenring_Bord2j           ) = 0.0000000000000000E+00  ! [-] Lock position in z\r\n" + 
				"marker.par (             13 ,       $M_Innenring_Bord2j           ) = 0.0000000000000000E+00  ! [-] Lock orientation in al\r\n" + 
				"marker.par (             14 ,       $M_Innenring_Bord2j           ) = 0.0000000000000000E+00  ! [-] Lock orientation in be\r\n" + 
				"marker.par (             15 ,       $M_Innenring_Bord2j           ) = 0.0000000000000000E+00  ! [-] Lock orientation in ga\r\n" + 
				"marker.par (             16 ,       $M_Innenring_Bord2j           ) = 0.0000000000000000E+00  ! [-] Set v_rel to zero"+
				"\r\n" +
				"\r\n" +
				"\r\n" +
				"marker.type (                       $M_MKaefig_Bord1              ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_MKaefig_Bord1              ) = $B_Kaefig              ! Body\r\n" + 
				"marker.pos (              3 ,       $M_MKaefig_Bord1              ) = {("+ var[0] +"-"+var[1]+")/2}  ! Position\r\n" + 
				"\r\n" + 
				"marker.type (                       $M_MKaefig_Bord2              ) = 1                       ! Type\r\n" + 
				"marker.parent (                     $M_MKaefig_Bord2              ) = $B_Kaefig              ! Body\r\n" + 
				"marker.pos (              3 ,       $M_MKaefig_Bord2              ) = {-("+ var[0] +"-"+var[1]+")/2} ! Position"+
				"\r\n" +
				"\r\n" +
				"\r\n";
			
			
			
	}
				
	
	
	towrite = towrite +
			"!**********************************************************************\r\n" + 
			"! Force Elements\r\n" + 
			"!**********************************************************************\r\n" +
			"\r\n";
			
	
	
	
	Integer uforcenr = (Integer) Settings.routineNrSpinner1.getValue();
	
	Integer wk_N	= ZyrolaGui.wk_N;
     
	//Wk-Lb-Kontakt
		addtowrite(uforcenr,1,wk_N);
		addtowrite(uforcenr,2,wk_N);
	
	// Bord-Force	
		if (ZyrolaGui.bordkrfte_index != 0) {
			//Borde  case of 0 ="NU"(2 A), 1= "N" (2I), 2= "NJ" (2A/1I), 3 = "NUP" (2A/2I)
			
			switch (i_case) {
				
			case 0 : addtowrite(uforcenr,3,wk_N);break;
			case 1 : addtowrite(uforcenr,4,wk_N);break;
			case 2 : addtowrite(uforcenr,3,wk_N);addtowrite(uforcenr,4,wk_N);break;
			case 3 : addtowrite(uforcenr,3,wk_N);addtowrite(uforcenr,4,wk_N);break;		
			}			
		}
	
	if (ZyrolaGui.kaefigindex == 1){
		addtowrite(uforcenr,5,wk_N);
	}
		
		
	if (ZyrolaGui.kaefigindex == 2){
		addtowrite(uforcenr,6,wk_N);		
		if (ZyrolaGui.mkaefigindex != 0)addtowrite(uforcenr,7,2);			
	}

	
		
		

	
	if (ZyrolaGui.flex_ar != 0) {
		towrite = towrite + 
				"!**********************************************************************\r\n" + 
				"! Input Functions\r\n" + 
				"!**********************************************************************\r\n" + 
				"ifctn.file (                        $I_ARflex                     ) = 'ARflex.afs'            ! File\r\n" + 
				"ifctn.x (                 1 ,       $I_ARflex                     ) = 0.0000000000000000E+00  ! X values\r\n" + 
				"ifctn.x (                 2 ,       $I_ARflex                     ) = 1.0000000000000000E+00  ! X values\r\n" + 
				"ifctn.y (                 1 ,       $I_ARflex                     ) = 0.0000000000000000E+00  ! Y values\r\n" + 
				"ifctn.y (                 2 ,       $I_ARflex                     ) = 0.0000000000000000E+00  ! Y values\r\n" + 
				"";
	
	
	}
		
	//!! Force-Einstellungen für Einzellager!!	
	if (ZyrolaGui.s_lagermod == 0) {
		
		towrite = towrite + 
				"force.type (                        $F_Force_Torque               ) = 93                      ! Type\r\n" + 
				"force.from (                        $F_Force_Torque               ) = $M_Isys                 ! From Marker\r\n" + 
				"force.to (                          $F_Force_Torque               ) = $M_Innenring_BRF        ! To Marker\r\n"+
				"force.disabled (                    $F_Force_Torque               ) = 0                       ! Disabled\r\n" + 
				"force.par (               1 ,       $F_Force_Torque               ) = 0.0000000000000000E+00  ! [-] nr_u for f_x\r\n" + 
				"force.par (               2 ,       $F_Force_Torque               ) = $UVectorElement_U5      ! [-] nr_u for f_y\r\n" + 
				"force.par (               3 ,       $F_Force_Torque               ) = $UVectorElement_U8      ! [-] nr_u for f_z\r\n" + 
				"force.par (               4 ,       $F_Force_Torque               ) = 0.0000000000000000E+00  ! [-] nr_u for l_x\r\n" + 
				"force.par (               5 ,       $F_Force_Torque               ) = 0.0000000000000000E+00  ! [-] nr_u for l_y\r\n" + 
				"force.par (               6 ,       $F_Force_Torque               ) = $UVectorElement_U3      ! [-] nr_u for l_z\r\n" + 
				"\r\n";
		

		
		
		towrite = towrite +
				"!**********************************************************************\r\n" + 
				"! Excitations\r\n" + 
				"!**********************************************************************\r\n" + 
				"excit.type (                        $T_cosspeed                   ) = 3                       ! Type\r\n" + 
				"excit.par (               1 ,       $T_cosspeed                   ) = {$G_Simulationsparameter.$_ZyRoLA_dREH_U }      ! [Hz] Derivative at end fd(t)\r\n" + 
				"excit.par (               2 ,       $T_cosspeed                   ) = {$G_Simulationsparameter.$_ZyRoLA_simrampent }  ! [s] Run-up time T\r\n" + 
				"excit.par (               3 ,       $T_cosspeed                   ) = 0.0000000000000000E+00  ! [-] Offset f0\r\n" + 
				"excit.par (               4 ,       $T_cosspeed                   ) = 0.0000000000000000E+00  ! [s] Offset t0\r\n" + 
				"\r\n" + 
				"excit.type (                        $T_Fr_Dyn                     ) = 3                       ! Type\r\n" + 
				"excit.par (               1 ,       $T_Fr_Dyn                     ) = {$G_Simulationsparameter.$_ZyRoLA_FR } ! [Hz] Derivative at end fd(t)\r\n" + 
				"excit.par (               2 ,       $T_Fr_Dyn                     ) = {$G_Simulationsparameter.$_ZyRoLA_simrampent }  ! [s] Run-up time T\r\n" + 
				"excit.par (               3 ,       $T_Fr_Dyn                     ) = 0.0000000000000000E+00  ! [-] Offset f0\r\n" + 
				"excit.par (               4 ,       $T_Fr_Dyn                     ) = 0.0000000000000000E+00  ! [s] Offset t0\r\n" + 
				"\r\n" + 
				"excit.type (                        $T_Fa_step                    ) = 3                       ! Type\r\n" + 
				"excit.par (               1 ,       $T_Fa_step                    ) = {$G_Simulationsparameter.$_ZyRoLA_FA }  ! [Hz] Derivative at end fd(t)\r\n" + 
				"excit.par (               2 ,       $T_Fa_step                    ) = {$G_Simulationsparameter.$_ZyRoLA_simrampent }  ! [s] Run-up time T\r\n" + 
				"excit.par (               3 ,       $T_Fa_step                    ) = 0.0000000000000000E+00  ! [-] Offset f0\r\n" + 
				"excit.par (               4 ,       $T_Fa_step                    ) = 0.0000000000000000E+00  ! [s] Offset t0\r\n" + 
				"\r\n" + 
				"!**********************************************************************\r\n" + 
				"! u-Vector\r\n" + 
				"!**********************************************************************\r\n" + 
				"uele.type (                         $UVectorElement_U1            ) = 1                       ! Type\r\n" + 
				"uele.excit (                        $UVectorElement_U1            ) = $T_cosspeed             ! Referenced Excitation\r\n" + 
				"uele.index (                        $UVectorElement_U1            ) = 0                       ! Index\r\n" + 
				"\r\n" + 
				"uele.type (                         $UVectorElement_U2            ) = 1                       ! Type\r\n" + 
				"uele.excit (                        $UVectorElement_U2            ) = $T_cosspeed             ! Referenced Excitation\r\n" + 
				"uele.index (                        $UVectorElement_U2            ) = 1                       ! Index\r\n" + 
				"\r\n" + 
				"uele.type (                         $UVectorElement_U3            ) = 1                       ! Type\r\n" + 
				"uele.excit (                        $UVectorElement_U3            ) = $T_cosspeed             ! Referenced Excitation\r\n" + 
				"uele.index (                        $UVectorElement_U3            ) = 2                       ! Index\r\n" + 
				"\r\n" + 
				"uele.type (                         $UVectorElement_U4            ) = 1                       ! Type\r\n" + 
				"uele.excit (                        $UVectorElement_U4            ) = $T_Fr_Dyn               ! Referenced Excitation\r\n" + 
				"uele.index (                        $UVectorElement_U4            ) = 0                       ! Index\r\n" + 
				"\r\n" + 
				"uele.type (                         $UVectorElement_U5            ) = 1                       ! Type\r\n" + 
				"uele.excit (                        $UVectorElement_U5            ) = $T_Fr_Dyn               ! Referenced Excitation\r\n" + 
				"uele.index (                        $UVectorElement_U5            ) = 1                       ! Index\r\n" + 
				"\r\n" + 
				"uele.type (                         $UVectorElement_U6            ) = 1                       ! Type\r\n" + 
				"uele.excit (                        $UVectorElement_U6            ) = $T_Fr_Dyn               ! Referenced Excitation\r\n" + 
				"uele.index (                        $UVectorElement_U6            ) = 2                       ! Index\r\n" + 
				"\r\n" + 
				"uele.type (                         $UVectorElement_U7            ) = 1                       ! Type\r\n" + 
				"uele.excit (                        $UVectorElement_U7            ) = $T_Fa_step              ! Referenced Excitation\r\n" + 
				"uele.index (                        $UVectorElement_U7            ) = 0                       ! Index\r\n" + 
				"\r\n" + 
				"uele.type (                         $UVectorElement_U8            ) = 1                       ! Type\r\n" + 
				"uele.excit (                        $UVectorElement_U8            ) = $T_Fa_step              ! Referenced Excitation\r\n" + 
				"uele.index (                        $UVectorElement_U8            ) = 1                       ! Index\r\n" + 
				"\r\n" + 
				"uele.type (                         $UVectorElement_U9            ) = 1                       ! Type\r\n" + 
				"uele.excit (                        $UVectorElement_U9            ) = $T_Fa_step              ! Referenced Excitation\r\n" + 
				"uele.index (                        $UVectorElement_U9            ) = 2                       ! Index" + 
				"\r\n";
		
	

	}

	
	
	
	
	
	
	pw.write(towrite);
	if ( pw != null ) pw.close();
			    
}

	
	public void addtowrite(Integer Nr, Integer contactloc, Integer F_Nr) {
		
		String F_bez,M_from,M_to,M_Lfb_a,M_Kaefig,M_Kaefig_i,M_Kaefig_au,M_Lfb_brd,M_Kfg_brd;
		Integer i,kaefigmod;
		double num_bord_ctcs;
		String[] ia = {"","Aussenring","Innenring"};
		
		
		F_bez="0" ;
		M_from="0" ;
		M_to="0" ;
		M_Lfb_a="0" ;
		M_Kaefig="0" ;
		M_Kaefig_i="0" ;
		M_Kaefig_au="0" ;
		M_Lfb_brd="0" ;
		M_Kfg_brd="0";
		
		kaefigmod = 0;
		num_bord_ctcs = 0;
		
		
		for ( i = 1; i<= F_Nr; i++) {
		
			switch (contactloc){
			
			case 1 : F_bez = "$Fa_F" + i; M_from= "$M_WK"+i;M_to ="$M_Aussenring_WK"+i;break;
			case 2 : F_bez = "$Fi_F" + i; M_from= "$M_WK"+i;M_to ="$M_Innenring_WK"+i;break;
			
			case 3 : F_bez = "$Fa_Bord" + i; M_from= "$M_WK"+i; M_to ="$M_Aussenring_WK"+i; M_Lfb_a ="$M_Aussenring_Aussen";  M_Kaefig_i = "$M_Kaefig"+i; num_bord_ctcs = 2;break;
			case 4 : F_bez = "$Fi_Bord" + i; M_from= "$M_WK"+i; M_to ="$M_Innenring_WK"+i; M_Lfb_a ="$M_Innenring_Aussen" ;  M_Kaefig_i = "$M_Kaefig"+i; num_bord_ctcs = ZyrolaGui.ir_num_contacts;break;
			
			case 5 : F_bez = "$F"+i+"_Taschenfeder"; M_from= "$M_WK"+i; M_to = "$M_Kaefig"+i+"_WK"+i ; M_Kaefig_i = "$M_Kaefig"+i; break;
			case 6 : F_bez = "$F_Kontaktkaefig_"+i ; M_from= "$M_WK"+i; M_to = "$M_Kaefig"+i+"_WK"+i ; M_Kaefig_i = "$M_Kaefig"+i; M_Kaefig = "$M_Kaefig"; M_Kaefig_au = "$M_Kaefig_Aussen"; break; 
			
			
			case 7:  F_bez = "$F_Kaefigfuehrungbord_"+i; M_from="$M_MKaefig_Bord"+i; M_to="$M_"+ia[ZyrolaGui.mkaefigindex]+"_Bord"+i+"j";
					 M_Lfb_brd="$M_"+ia[ZyrolaGui.mkaefigindex]+"_Bord"+i; M_Kfg_brd="$M_MKaefig_Bord"+i; kaefigmod=ZyrolaGui.mkaefigindex+4 ;break;
			
			}
		
		
		
		
		
		
		
		
		
		
		
		towrite = towrite +
				
				"\r\n" +
				"\r\n" +
				"\r\n" +
				"\r\n" + 
				"force.type (                        "+F_bez+"                        ) = "+Nr+"                      ! Type\r\n" + 
				"force.from (                        "+F_bez+"                        ) = "+M_from+"                  ! From Marker\r\n" + 
				"force.to (                          "+F_bez+"                        ) = "+M_to+"        ! To Marker\r\n" + 
				"force.disabled (                    "+F_bez+"                        ) = 0                       ! Disabled\r\n" + 
				"force.par (               1 ,       "+F_bez+"                        ) = $M_Aussenring_Ref       ! [-] Marker Aussenring\r\n" + 
				"force.par (               2 ,       "+F_bez+"                        ) = $M_Innenring_BRF        ! [-] Marker Innenring\r\n" + 
				"force.par (               3 ,       "+F_bez+"                        ) = $M_WK"+i+"                  ! [-] Marker Waelzkoerper\r\n" + 
				"force.par (               4 ,       "+F_bez+"                        ) = $M_WK"+i+"_aussen           ! [-] Marker Waelzkoerper aussen\r\n" + 
				"force.par (               5 ,       "+F_bez+"                        ) = $M_Isys                 ! [-] Marker Ground\r\n" + 
				"force.par (               6 ,       "+F_bez+"                        ) = "+contactloc+"     ! [-] Kontaktlocation\r\n" + 
				"force.par (               7 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_AR_LFB_D*1000 } ! [-] AR Laufbahndurchmesser\r\n" + 
				"force.par (               8 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_prorad_AR*1000 } ! [-] AR Profilradius\r\n" + 
				"force.par (               9 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_IR_LFB_D*1000 } ! [-] IR Laufbahndurchmesser\r\n" + 
				"force.par (              10 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_prorad_IR*1000 } ! [-] IR Profilradius\r\n" + 
				"force.par (              11 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_WK_z*1000 } ! [-] WK Länge\r\n" + 
				"force.par (              12 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_WK_D*1000 } ! [-] WK Durchmesser\r\n" + 
				"force.par (              13 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_prorad_WK } ! [-] WK Profilradius\r\n" + 
				"force.par (              14 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_protype } ! [-] Profil Type\r\n" + 
				"force.par (              15 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_WKpro_ap } ! [-] WK-profil nach DIN-ISO\r\n" + 
				"force.par (              16 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_WKpro_cp } ! [-] WK-effektive Länge\r\n" + 
				"force.par (              17 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_WKpro_dp } ! [-] WK-Länge Zylinderstück\r\n" + 
				"force.par (              18 ,       "+F_bez+"                        ) = $G_GeometrieParameter.$_ZyRoLA_WKpro_kp ! [-] WK-Beginn Geradenstück\r\n" + 
				"force.par (              19 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_WKpro_rk } ! [-] WK-profil Kantenradius\r\n" + 
				"force.par (              20 ,       "+F_bez+"                        ) = { $G_GeometrieMaterialdaten.$_ZyRoLA_IR_E } ! [-] IR_E\r\n" + 
				"force.par (              21 ,       "+F_bez+"                        ) = { $G_GeometrieMaterialdaten.$_ZyRoLA_IR_v } ! [-] IR_v\r\n" + 
				"force.par (              22 ,       "+F_bez+"                        ) = $G_GeometrieMaterialdaten.$_ZyRoLA_AR_E ! [-] AR_E\r\n" + 
				"force.par (              23 ,       "+F_bez+"                        ) = { $G_GeometrieMaterialdaten.$_ZyRoLA_AR_v     	 } ! [-] AR_v\r\n" + 
				"force.par (              24 ,       "+F_bez+"                        ) = { $G_GeometrieMaterialdaten.$_ZyRoLA_WK_E      	 } ! [-] WK_E\r\n" + 
				"force.par (              25 ,       "+F_bez+"                        ) = { $G_GeometrieMaterialdaten.$_ZyRoLA_WK_v   		 } ! [-] WK_v\r\n" + 
				"force.par (              26 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_race_f_mod } ! [-] Festkörperreibmodell\r\n" + 
				"force.par (              27 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_race_f_EHD } ! [-] EHD-Reibung  0-aus 1-an\r\n" + 
				"force.par (              28 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_race_lub_mod } ! [-] Schmierstoff - race_lub_mod\r\n" + 
				"force.par (              29 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_tau_mod } ! [-] Schubspannung - tau_mod\r\n" + 
				"force.par (              30 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_filmT_mod } ! [-] Ther_Korr-faktor - filmT_mod\r\n" + 
				"force.par (              31 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_comprT_mod } ! [-] Erwärmung - comprT_mod\r\n" + 
				"force.par (              32 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_vel_s } ! [-] vel_s\r\n" + 
				"force.par (              33 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_vel_d } ! [-] vel_d\r\n" + 
				"force.par (              34 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_mu_s		 } ! [-] mu_s\r\n" + 
				"force.par (              35 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_mu_d   		 } ! [-] mu_d\r\n" + 
				"force.par (              36 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_lubtmp     	 } ! [-] lubtmp\r\n" + 
				"force.par (              37 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_etaZero    	 } ! [-] etaZero\r\n" + 
				"force.par (              38 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_alphaT } ! [-] alphaT\r\n" + 
				"force.par (              39 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_alphaP } ! [-] alphaP\r\n" + 
				"force.par (              40 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_lambdaZero } ! [-] lambdaZero\r\n" + 
				"force.par (              41 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_alphaLambda } ! [-] alphaLambda\r\n" + 
				"force.par (              42 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_C1         	 } ! [-] C1\r\n" + 
				"force.par (              43 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_C2         	 } ! [-] C2\r\n" + 
				"force.par (              44 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_AV         	 } ! [-] AV\r\n" + 
				"force.par (              45 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_Bv         	 } ! [-] Bv\r\n" + 
				"force.par (              46 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_CV         	 } ! [-] CV\r\n" + 
				"force.par (              47 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_DR         	 } ! [-] DR\r\n" + 
				"force.par (              48 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_ER         	 } ! [-] ER\r\n" + 
				"force.par (              49 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_pR         	 } ! [-] pR\r\n" + 
				"force.par (              50 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_B         	 } ! [-] B\r\n" + 
				"force.par (              51 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_C         	 } ! [-] C\r\n" + 
				"force.par (              52 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_K         	 } ! [-] K\r\n" + 
				"force.par (              53 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_a1         	 } ! [-] a1\r\n" + 
				"force.par (              54 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_a2         	 } ! [-] a2\r\n" + 
				"force.par (              55 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_b1         	 } ! [-] b1\r\n" + 
				"force.par (              56 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_b2         	 } ! [-] b2\r\n" + 
				"force.par (              57 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_rhoZero    	 } ! [-] rhoZero\r\n" + 
				"force.par (              58 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_tauRheo } ! [-] tauRheo\r\n" + 
				"force.par (              59 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_sigma    	 } ! [-] sigma\r\n" + 
				"force.par (              60 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_BZH       	 } ! [-] BZH\r\n" + 
				"force.par (              61 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_CZH        	 } ! [-] CZH\r\n" + 
				"force.par (              62 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_alphaHys } ! [-] alphaHys\r\n" + 
				"force.par (              63 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_scaleHys  	 } ! [-] scaleHys\r\n" + 
				"force.par (              64 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_cr         	 } ! [-] cr\r\n" + 
				"force.par (              65 ,       "+F_bez+"                        ) = { $G_SchmierungReibungDaten.$_ZyRoLA_rexp } ! [-] rexp\r\n" + 
				"force.par (              66 ,       "+F_bez+"                        ) = { $G_Daempfungsdaten.$_ZyRoLA_d_mod } ! [-] d_mod\r\n" + 
				"force.par (              67 ,       "+F_bez+"                        ) = { $G_Daempfungsdaten.$_ZyRoLA_p_t } ! [-] p_t\r\n" + 
				"force.par (              68 ,       "+F_bez+"                        ) = { $G_Daempfungsdaten.$_ZyRoLA_d_max      	 } ! [-] d_max\r\n" + 
				"force.par (              69 ,       "+F_bez+"                        ) = { $G_Daempfungsdaten.$_ZyRoLA_K0         	 } ! [-] K0\r\n" + 
				"force.par (              70 ,       "+F_bez+"                        ) = { $G_Daempfungsdaten.$_ZyRoLA_KR         	 } ! [-] KR\r\n" + 
				"force.par (              71 ,       "+F_bez+"                        ) = { $G_Daempfungsdaten.$_ZyRoLA_KL         	 } ! [-] KL\r\n" + 
				"force.par (              72 ,       "+F_bez+"                        ) = { $G_Daempfungsdaten.$_ZyRoLA_KE         	 } ! [-] KE\r\n" + 
				"force.par (              73 ,       "+F_bez+"                        ) = { $G_Daempfungsdaten.$_ZyRoLA_Keta } ! [-] Keta\r\n" + 
				"force.par (              74 ,       "+F_bez+"                        ) = { $G_Daempfungsdaten.$_ZyRoLA_Kalpha } ! [-] Kalpha\r\n" + 
				"force.par (              75 ,       "+F_bez+"                        ) = { $G_Daempfungsdaten.$_ZyRoLA_Kq         	 } ! [-] Kq\r\n" + 
				"force.par (              76 ,       "+F_bez+"                        ) = { $G_Daempfungsdaten.$_ZyRoLA_Ku         	 } ! [-] Ku\r\n" + 
				"force.par (              77 ,       "+F_bez+"                        ) = { $G_Daempfungsdaten.$_ZyRoLA_Kf         	 } ! [-] Kf\r\n" + 
				"force.par (              78 ,       "+F_bez+"                        ) = { $G_Daempfungsdaten.$_ZyRoLA_fe } ! [-] fe\r\n" + 
				"force.par (              79 ,       "+F_bez+"                        ) = { $G_sonstigeParameter.$_ZyRoLA_ctctype_LB } ! [-] ctctype_LB\r\n" + 
				"force.par (              80 ,       "+F_bez+"                        ) = { $G_sonstigeParameter.$_ZyRoLA_no_gauss_LB } ! [-] no_gauss_LB\r\n" + 
				"force.par (              81 ,       "+F_bez+"                        ) = { $G_sonstigeParameter.$_ZyRoLA_cf_pnts_LB } ! [-] cf_pnts_LB\r\n" + 
				"force.par (              82 ,       "+F_bez+"                        ) = { $G_sonstigeParameter.$_ZyRoLA_cf_p_max_LB } ! [-] cf_p_max_LB\r\n" + 
				"force.par (              83 ,       "+F_bez+"                        ) = { $G_sonstigeParameter.$_ZyRoLA_gfo_id_AR } ! [-] gfo_id_AR\r\n" + 
				"force.par (              84 ,       "+F_bez+"                        ) = { $G_sonstigeParameter.$_ZyRoLA_gfo_id_IR } ! [-] gfo_id_IR\r\n" + 
				"force.par (              85 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_AR_Dicke } ! [-] AR_Dicke\r\n" + 
				"force.par (              86 ,       "+F_bez+"                        ) = { $G_sonstigeParameter.$_ZyRoLA_flex_ar } ! [-] Flexib. AR 0-aus 1-an\r\n" + 
				"force.par (              87 ,       "+F_bez+"                        ) = { $G_sonstigeParameter.$_ZyRoLA_FE_NForce_flex } ! [-] Flexib. AR: Kraft aus FE\r\n" + 
				"force.par (              88 ,       "+F_bez+"                        ) = { $G_sonstigeParameter.$_ZyRoLA_ASCII } ! [-] ASCII-Ausgabe 0-aus 1-EL 2-SL\r\n" + 
				"force.par (              89 ,       "+F_bez+"                        ) = "+M_Lfb_a+ "     ! [-] Marker Laufbahn Aussen\r\n" + 
				"force.par (              91 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_WK_D/2.0*1000 } ! [-] Radius des Wälzkörpers\r\n" + 
				"force.par (              92 ,       "+F_bez+"                        ) = $G_GeometrieParameter.$_ZyRoLA_WKpro_rk ! [-] Kantenradius des Wälzkörpers\r\n" + 
				"force.par (              93 ,       "+F_bez+"                        ) = $G_Bordkraefte.$_ZyRoLA_AR_LFB_BRD ! [-] Breite Laufbahn Außenring\r\n" + 
				"force.par (              94 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_AR_LFB_D*1000 } ! [-] Bord-durch Außen-Außenring\r\n" + 
				"force.par (              95 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_AR_Bord_D*1000 } ! [-] Bord-durch Innen-Außenring\r\n" + 
				"force.par (              96 ,       "+F_bez+"                        ) = { ($G_GeometrieParameter.$_ZyRoLA_AR_LFB_D-$G_GeometrieParameter.$_ZyRoLA_AR_Bord_D)/4.0*1000 } ! [-] Bord Dicke-Außenring\r\n" + 
				"force.par (              97 ,       "+F_bez+"                        ) = $G_Bordkraefte.$_ZyRoLA_IR_LFB_BRD ! [-] Breite Laufbahn Innenring\r\n" + 
				"force.par (              98 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_IR_Bord_D*1000 } ! [-] Bord-durch Außen-Innenring\r\n" + 
				"force.par (              99 ,       "+F_bez+"                        ) = { $G_GeometrieParameter.$_ZyRoLA_IR_LFB_D*1000 } ! [-] Bord-durch Innen-Innenring\r\n" + 
				"force.par (             100 ,       "+F_bez+"                        ) = { ($G_GeometrieParameter.$_ZyRoLA_IR_Bord_D-$G_GeometrieParameter.$_ZyRoLA_IR_LFB_D)/4.0*1000 } ! [-] Bord Dicke-Innenring\r\n" + 
				"force.par (             101 ,       "+F_bez+"                        ) = $G_Bordkraefte.$_ZyRoLA_IR_oew ! [-] Bordoeffnungswinkel IR\r\n" + 
				"force.par (             102 ,       "+F_bez+"                        ) = $G_Bordkraefte.$_ZyRoLA_AR_oew ! [-] Bordoeffnungswinkel AR\r\n" + 
				"force.par (             103 ,       "+F_bez+"                        ) = { (($G_GeometrieParameter.$_ZyRoLA_AR_LFB_D+$G_GeometrieParameter.$_ZyRoLA_IR_LFB_D)/4)*1000 } ! [-] Teilkreisradius\r\n" + 
				"force.par (             104 ,       "+F_bez+"                        ) = 2.0000000000000000E+00  ! [-] Festkörperreibmodell Bord\r\n" + 
				"force.par (             105 ,       "+F_bez+"                        ) = $G_Bordkraefte.$_ZyRoLA_vel_s_BRD ! [-] vel_s_BRD\r\n" + 
				"force.par (             106 ,       "+F_bez+"                        ) = $G_Bordkraefte.$_ZyRoLA_vel_d_BRD ! [-] vel_d_BRD\r\n" + 
				"force.par (             107 ,       "+F_bez+"                        ) = $G_Bordkraefte.$_ZyRoLA_mu_s_BRD ! [-] mu_s_BRD\r\n" + 
				"force.par (             108 ,       "+F_bez+"                        ) = $G_Bordkraefte.$_ZyRoLA_mu_d_BRD ! [-] mu_d_BRD\r\n" + 
				"force.par (             109 ,       "+F_bez+"                        ) = $G_Bordkraefte.$_ZyRoLA_p_t_BRD ! [-] Durchdringung-max.dämpfungBRD\r\n" + 
				"force.par (             110 ,       "+F_bez+"                        ) = $G_Bordkraefte.$_ZyRoLA_d_max_BRD ! [-] Maximale Dämpfung BRD\r\n" + 
				"force.par (             111 ,       "+F_bez+"                        ) = "+M_Kaefig_i+"              ! [-] Marker_Käfig_Mittel BRD\r\n" + 
				"force.par (             112 ,       "+F_bez+"                        ) = "+num_bord_ctcs+"  ! [-] Anzahl Bordkontakte\r\n" +
				"force.par (             113 ,       "+F_bez+"                        ) = "+M_Kaefig_i+"              ! [-] Marker Käfig Tasche\r\n" +
				"force.par (             114 ,       "+F_bez+"                        ) = {$G_Kraefte_Taschenfederkaefig.$_ZyRoLA_K_tspiel_tan }  ! [-] Tangential Taschenspiel\r\n" + 
				"force.par (             115 ,       "+F_bez+"                        ) = {$G_Kraefte_Taschenfederkaefig.$_ZyRoLA_K_tspiel_rad }  ! [-] Radial Taschenspiel\r\n" + 
				"force.par (             116 ,       "+F_bez+"                        ) = {$G_Kraefte_Taschenfederkaefig.$_ZyRoLA_K_tspiel_ax  }  ! [-] Axial Taschenspiel\r\n" + 
				"force.par (             117 ,       "+F_bez+"                        ) = "+M_Kaefig+"               ! [-] Marker Käfig\r\n" + 
				"force.par (             118 ,       "+F_bez+"                        ) = "+M_Kaefig_au+"        ! [-] Marker Käfig aussen\r\n" + 
				"force.par (             119 ,       "+F_bez+"                        ) = {$G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_tasche * 1000}  ! [-] Käfig Taschendurchmesser\r\n" + 
				"force.par (             120 ,       "+F_bez+"                        ) = {$G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_DickeSteg * 1000} ! [-] Dicke des Steges\r\n" + 
				"force.par (             121 ,       "+F_bez+"                        ) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_E  ! [-] Elastizitätsmodul Käfigs\r\n" + 
				"force.par (             122 ,       "+F_bez+"                        ) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_v  ! [-] Querkontraktionszahl Käfig\r\n" + 
				"force.par (             123 ,       "+F_bez+"                        ) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_race_f_mod  ! [-] Festkörperreibmodell KF\r\n" + 
				"force.par (             124 ,       "+F_bez+"                        ) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_vel_s_KF    ! [-] vel_s KF\r\n" + 
				"force.par (             125 ,       "+F_bez+"                        ) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_vel_d_KF    ! [-] vel_d KF\r\n" + 
				"force.par (             126 ,       "+F_bez+"                        ) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_mu_s_KF     ! [-] mu_s  KF\r\n" + 
				"force.par (             127 ,       "+F_bez+"                        ) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_mu_d_KF     ! [-] mu_d  KF\r\n" + 
				"force.par (             128 ,       "+F_bez+"                        ) = $G_Kraefte_Kontaktfaefig.$_KontaktModell_WK_KF   		  ! [-] Kontakt Modell KF_WK\r\n" + 
				"force.par (             129 ,       "+F_bez+"                        ) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_cf_pnts_KF   		  ! [-] Anzahl der Curvefit-Punkte KF\r\n" + 
				"force.par (             130 ,       "+F_bez+"                        ) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_cf_p_max_KF  		  ! [-] P_Max für Curvefit KF\r\n" + 
				"force.par (             131 ,       "+F_bez+"                        ) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_d_mod   ! [-] d_mod  KF\r\n" + 
				"force.par (             132 ,       "+F_bez+"                        ) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_p_t  			  ! [-] p_t    KF\r\n" + 
				"force.par (             133 ,       "+F_bez+"                        ) = $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_d_max		   		  ! [-] d_max  KF\r\n" + 
				"force.par (             134 ,       "+F_bez+"                        ) = "+M_Kfg_brd+"	 ! [-] Marker Käfig Bord\r\n" + 
				"force.par (             135 ,       "+F_bez+"                        ) = "+M_Lfb_brd+"  ! [-] Marker Laufbahnbord\r\n" + 
				"force.par (             136 ,       "+F_bez+"                        ) = "+kaefigmod+"  ! [-] Käfigmod\r\n" + 
				"force.par (             137 ,       "+F_bez+"                        ) =  { $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_aussen*1000  }   ! [-] Aussendurchmesser Käfig\r\n" + 
				"force.par (             138 ,       "+F_bez+"                        ) =  { $G_Kraefte_Kontaktfaefig.$_ZyRoLA_K_D_innen*1000  } ! [-] Innendurchmesser Käfig\r\n" +
				"\r\n" +                             
				"\r\n" +                             
				"\r\n" +                             
				"\r\n";                              
				                                     
		}	                                             
	}                                                
                                                     
                                                     
	
                                                     
                                                     
}                                                    
                                                     
	                                                 
                                                     

