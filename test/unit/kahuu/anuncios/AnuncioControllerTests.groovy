package kahuu.anuncios



import org.junit.*
import grails.test.mixin.*

@TestFor(AnuncioController)
@Mock(Anuncio)
class AnuncioControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/anuncio/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.anuncioInstanceList.size() == 0
        assert model.anuncioInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.anuncioInstance != null
    }

    void testSave() {
        controller.save()

        assert model.anuncioInstance != null
        assert view == '/anuncio/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/anuncio/show/1'
        assert controller.flash.message != null
        assert Anuncio.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/anuncio/list'

        populateValidParams(params)
        def anuncio = new Anuncio(params)

        assert anuncio.save() != null

        params.id = anuncio.id

        def model = controller.show()

        assert model.anuncioInstance == anuncio
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/anuncio/list'

        populateValidParams(params)
        def anuncio = new Anuncio(params)

        assert anuncio.save() != null

        params.id = anuncio.id

        def model = controller.edit()

        assert model.anuncioInstance == anuncio
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/anuncio/list'

        response.reset()

        populateValidParams(params)
        def anuncio = new Anuncio(params)

        assert anuncio.save() != null

        // test invalid parameters in update
        params.id = anuncio.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/anuncio/edit"
        assert model.anuncioInstance != null

        anuncio.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/anuncio/show/$anuncio.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        anuncio.clearErrors()

        populateValidParams(params)
        params.id = anuncio.id
        params.version = -1
        controller.update()

        assert view == "/anuncio/edit"
        assert model.anuncioInstance != null
        assert model.anuncioInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/anuncio/list'

        response.reset()

        populateValidParams(params)
        def anuncio = new Anuncio(params)

        assert anuncio.save() != null
        assert Anuncio.count() == 1

        params.id = anuncio.id

        controller.delete()

        assert Anuncio.count() == 0
        assert Anuncio.get(anuncio.id) == null
        assert response.redirectedUrl == '/anuncio/list'
    }
}
