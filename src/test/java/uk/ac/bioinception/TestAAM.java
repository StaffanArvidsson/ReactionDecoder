package uk.ac.bioinception;

import org.junit.Test;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IReaction;
import org.openscience.cdk.silent.Reaction;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmiFlavor;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.smiles.SmilesParser;

import uk.ac.ebi.reactionblast.mechanism.MappingSolution;
import uk.ac.ebi.reactionblast.mechanism.ReactionMechanismTool;
import uk.ac.ebi.reactionblast.tools.StandardizeReaction;

public class TestAAM {
	
	@Test
	public void testUsingRDT() throws Exception {

		//	        	ReaccsMDLRXNReader reader = new ReaccsMDLRXNReader(TestMCSS.class.getResourceAsStream("/resources/rireg1.rdf"));
		//	        	IReaction r = reader.read(new Reaction());
		//	        	reader.close();

		SmilesParser sp = new SmilesParser(SilentChemObjectBuilder.getInstance());
		//	        	IAtomContainer reactant = sp.parseSmiles("C1=CC=CC=C1");
		//	  		  IAtomContainer product = sp.parseSmiles("C1=CC2=C(C=C1)C=CC=C2");
		IAtomContainer reactant = sp.parseSmiles("CCC(CC)(C(=O)NC(=O)NC(=O)C)Br");
		IAtomContainer product = sp.parseSmiles("C/C=C(/CC)\\C(=O)NC(=O)N");

		IReaction r = new Reaction();
		r.addReactant(reactant);
		r.addProduct(product);

		SmilesGenerator sg = new SmilesGenerator(SmiFlavor.Isomeric | SmiFlavor.AtomAtomMap);

		ReactionMechanismTool tool = new ReactionMechanismTool(r, true, false, false, new StandardizeReaction());

		MappingSolution solution = tool.getSelectedSolution();
		
		System.err.println(sg.create(solution.getReaction()));



	}

}
