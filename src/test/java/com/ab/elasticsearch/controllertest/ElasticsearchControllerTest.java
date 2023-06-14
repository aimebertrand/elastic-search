    package com.ab.elasticsearch.controllertest;

    import com.ab.elasticsearch.controller.MediaController;
    import com.ab.elasticsearch.controller.EmpruntController;
    import com.ab.elasticsearch.controller.UtilisateurController;
    import com.ab.elasticsearch.mappings.Media;
    import com.ab.elasticsearch.mappings.Emprunt;
    import com.ab.elasticsearch.mappings.Utilisateur;
    import com.ab.elasticsearch.springdata.MediaRepository;
    import com.ab.elasticsearch.springdata.EmpruntRepository;
    import com.ab.elasticsearch.springdata.UtilisateurRepository;
    import org.junit.jupiter.api.AfterEach;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.InOrder;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;

    import java.time.LocalDate;
    import java.util.Date;
    import java.util.NoSuchElementException;
    import java.util.Optional;

    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.Mockito.*;

    public class ElasticsearchControllerTest {

        @Mock
        private MediaRepository mediaRepository;

        @Mock
        private EmpruntRepository empruntRepository;

        @Mock
        private UtilisateurRepository utilisateurRepository;

        @InjectMocks
        private MediaController mediaController;

        @InjectMocks
        private EmpruntController empruntController;

        @InjectMocks
        private UtilisateurController utilisateurController;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.openMocks(this);
        }

        @AfterEach
        public void cleanup() {
            reset(mediaRepository, empruntRepository, utilisateurRepository);
        }

        @Test
        public void testCreateMedia() {
            // Données de test
            Media media = new Media();
            media.setId(4000);
            media.setType("livre");
            media.setTitre("Le Seigneur des Anneaux");
            media.setAuteurRealisateurEditeur("J.R.R. Tolkien");
            media.setDisponibilite(true);

            // Mock de la méthode save du repository
            when(mediaRepository.save(any(Media.class))).thenReturn(media);

            // Appel de la méthode à tester
            Media createdMedia = mediaController.create(media);

            // Vérification des résultats
            verify(mediaRepository, times(1)).save(any(Media.class));
            assertEquals(media, createdMedia);
        }

        @Test
        public void testUpdateMedia() {
            // Données de test
            Media media = new Media();
            media.setId(4000);
            media.setType("livre");
            media.setTitre("Le Seigneur des Anneaux");
            media.setAuteurRealisateurEditeur("J.R.R. Tolkien");
            media.setDisponibilite(true);

            // Mock de la méthode findById du repository
            when(mediaRepository.findById(4000)).thenReturn(Optional.of(media));

            // Mock de la méthode save du repository
            when(mediaRepository.save(any(Media.class))).thenReturn(media);

            // Appel de la méthode à tester
            Media updatedMedia = mediaController.update(media);

            // Vérification des résultats
            //verify(mediaRepository, times(1)).findById(4000);
            verify(mediaRepository, times(1)).save(any(Media.class));
            assertEquals(media, updatedMedia);
        }

        @Test
        public void testDeleteMedia() {
            // Données de test
            int mediaId = 4000;

            // Appel de la méthode à tester
            String result = mediaController.delete(mediaId);

            // Vérification des résultats
           // verify(mediaRepository, times(1)).findById(mediaId);
            verify(mediaRepository, times(1)).deleteById(mediaId);
            assertEquals("Done", result);
        }



        @Test
        public void testRetrieveMedia_Success() {
            // Données de test
            int mediaId = 4000;
            Media media = new Media();
            media.setId(mediaId);
            media.setType("livre");
            media.setTitre("Le Seigneur des Anneaux");
            media.setAuteurRealisateurEditeur("J.R.R. Tolkien");
            media.setDisponibilite(true);

            // Mock de la méthode findById du repository
            when(mediaRepository.findById(mediaId)).thenReturn(Optional.of(media));

            // Appel de la méthode à tester
            Media retrievedMedia = mediaController.retrieve(mediaId);

            // Vérification des résultats
            verify(mediaRepository, times(1)).findById(mediaId);
            assertEquals(media, retrievedMedia);
        }

        @Test
        public void testRetrieveMedia_NotFound() {
            // Données de test
            int mediaId = 4000;

            // Mock de la méthode findById du repository
            when(mediaRepository.findById(mediaId)).thenReturn(Optional.empty());

            // Appel de la méthode à tester et vérification de l'exception
            assertThrows(NoSuchElementException.class, () -> mediaController.retrieve(mediaId));

            // Vérification des appels aux méthodes du repository
            verify(mediaRepository, times(1)).findById(mediaId);
        }

        @Test
        public void testCreateUtilisateur() {
            // Données de test
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(5000);
            utilisateur.setNom("Doe");
            utilisateur.setAdresse("Paris");

            // Mock de la méthode save du repository
            when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(utilisateur);

            // Appel de la méthode à tester
            Utilisateur createdUtilisateur = utilisateurController.create(utilisateur);

            // Vérification des résultats
            verify(utilisateurRepository, times(1)).save(any(Utilisateur.class));
            assertEquals(utilisateur, createdUtilisateur);
        }


        @Test
        public void testDeleteUtilisateur() {
            // Données de test
            int utilisateurId = 5000;

            // Appel de la méthode à tester
            String result = utilisateurController.delete(utilisateurId);

            // Vérification des résultats
            verify(utilisateurRepository, times(1)).deleteById(utilisateurId);
            assertEquals("Done", result);
        }

        @Test
        public void testRetrieveUtilisateur_Success() {
            // Données de test
            int utilisateurId = 5000;
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(utilisateurId);
            utilisateur.setNom("Doe");
            utilisateur.setAdresse("Paris");

            // Mock de la méthode findById du repository
            when(utilisateurRepository.findById(utilisateurId)).thenReturn(Optional.of(utilisateur));

            // Appel de la méthode à tester
            Utilisateur retrievedUtilisateur = utilisateurController.retrieve(utilisateurId);

            // Vérification des résultats
            verify(utilisateurRepository, times(1)).findById(utilisateurId);
            assertEquals(utilisateur, retrievedUtilisateur);
        }

        @Test
        public void testRetrieveUtilisateur_NotFound() {
            // Données de test
            int utilisateurId = 5000;

            // Mock de la méthode findById du repository
            when(utilisateurRepository.findById(utilisateurId)).thenReturn(Optional.empty());

            // Appel de la méthode à tester et vérification de l'exception
            assertThrows(NoSuchElementException.class, () -> utilisateurController.retrieve(utilisateurId));

            // Vérification des appels aux méthodes du repository
            verify(utilisateurRepository, times(1)).findById(utilisateurId);
        }

        @Test
        public void testCreateEmprunt() {
            // Données de test
            Emprunt emprunt = new Emprunt();
            emprunt.setId(6000);
            emprunt.setIdMedia(4000);
            emprunt.setIdUtilisateur(5000);
            emprunt.setDateEmprunt(new Date());
            emprunt.setDateRetour(new Date());

            // Mock de la méthode save du repository
            when(empruntRepository.save(any(Emprunt.class))).thenReturn(emprunt);

            // Appel de la méthode à tester
            Emprunt createdEmprunt = empruntController.create(emprunt);

            // Vérification des résultats
            verify(empruntRepository, times(1)).save(any(Emprunt.class));
            assertEquals(emprunt, createdEmprunt);
        }


        @Test
        public void testDeleteEmprunt() {
            // Données de test
            int empruntId = 6000;

            // Appel de la méthode à tester
            String result = empruntController.delete(empruntId);

            // Vérification des résultats
            verify(empruntRepository, times(1)).deleteById(empruntId);
            assertEquals("Done", result);
        }

        @Test
        public void testRetrieveEmprunt_Success() {
            // Données de test
            int empruntId = 6000;
            Emprunt emprunt = new Emprunt();
            emprunt.setId(empruntId);
            emprunt.setIdMedia(4000);
            emprunt.setId(5000);
            emprunt.setDateEmprunt(new Date());
            emprunt.setDateRetour(new Date());

            // Mock de la méthode findById du repository
            when(empruntRepository.findById(empruntId)).thenReturn(Optional.of(emprunt));

            // Appel de la méthode à tester
            Emprunt retrievedEmprunt = empruntController.retrieve(empruntId);

            // Vérification des résultats
            verify(empruntRepository, times(1)).findById(empruntId);
            assertEquals(emprunt, retrievedEmprunt);
        }

        @Test
        public void testRetrieveEmprunt_NotFound() {
            // Données de test
            int empruntId = 6000;

            // Mock de la méthode findById du repository
            when(empruntRepository.findById(empruntId)).thenReturn(Optional.empty());

            // Appel de la méthode à tester et vérification de l'exception
            assertThrows(NoSuchElementException.class, () -> empruntController.retrieve(empruntId));

            // Vérification des appels aux méthodes du repository
            verify(empruntRepository, times(1)).findById(empruntId);
        }



    }
