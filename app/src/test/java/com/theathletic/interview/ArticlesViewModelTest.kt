import com.theathletic.interview.articles
import com.theathletic.interview.articles.domain.model.ArticleUiModel
import com.theathletic.interview.articles.domain.usecase.GetArticlesWithAuthorsUseCase
import com.theathletic.interview.articles.ui.ArticleEvent
import com.theathletic.interview.articles.ui.ArticlesViewModel
import junit.framework.TestCase.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Unit tests for the [ArticlesViewModel] class.
 */
@ExperimentalCoroutinesApi
class ArticlesViewModelTest {

    @Mock
    private lateinit var articlesWithAuthorsUseCase: GetArticlesWithAuthorsUseCase
    private lateinit var viewModel: ArticlesViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    /**
     * Sets up the test environment before each test case.
     */
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        runBlocking {
            `when`(articlesWithAuthorsUseCase.invoke()).thenReturn(articles)
        }

        viewModel = ArticlesViewModel(articlesWithAuthorsUseCase)
    }

    /**
     * Cleans up the test environment after each test case.
     */
    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    /**
     * Test case for the [ArticlesViewModel.getArticleById] function,
     * verifying that it returns the correct article when found.
     */
    @Test
    fun `getArticleById returns correct article when found`() = testDispatcher.runBlockingTest {
        // Arrange
        val result = CompletableDeferred<ArticleUiModel?>()
        val job = launch {
            viewModel.getArticleById("2").collect { articleUiModel ->
                result.complete(articleUiModel)
            }
        }

        // Act
        val article = result.await()

        // Assert
        assertNotNull(article)
        assertEquals("Article 2 body", article?.article?.body)

        // Cleanup
        job.cancel()
    }

    /**
     * Test case for the [ArticlesViewModel.getArticleById] function,
     * verifying that it returns null when the article is not found.
     */
    @Test
    fun `getArticleById returns null when article not found`() = testDispatcher.runBlockingTest {
        // Arrange
        val result = CompletableDeferred<ArticleUiModel?>()
        val job = launch {
            viewModel.getArticleById("4").collect { articleUiModel ->
                result.complete(articleUiModel)
            }
        }

        // Act
        val article = result.await()

        // Assert
        assertNull(article)

        // Cleanup
        job.cancel()
    }

    /**
     * Test case for the [ArticlesViewModel.onArticleClicked] function,
     * verifying that it emits the [ArticleEvent.OpenArticle] event.
     */
    @Test
    fun `onArticleClicked emits OpenArticle event`() = testDispatcher.runBlockingTest {
        // Arrange
        val articleId = "1"
        val eventDeferred = CompletableDeferred<ArticleEvent?>()
        val expectedEvent = ArticleEvent.OpenArticle(articleId)

        val job = launch {
            viewModel.viewEvent.collect { event ->
                eventDeferred.complete(event)
            }
        }

        // Act
        val job1 = launch {
            viewModel.onArticleClicked(articleId)
        }

        // Assert
        val actualEvent = eventDeferred.await()
        assertTrue(actualEvent is ArticleEvent.OpenArticle)
        assertEquals(expectedEvent.articleId, (actualEvent as ArticleEvent.OpenArticle).articleId)

        // Cleanup
        job.cancel()
        job1.cancel()
    }
}
