package kahuu.general



import kahuu.general.auto.CiudadController;

import org.junit.*
import grails.test.mixin.*

@TestFor(CiudadController)
@Mock(Ciudad)
class CiudadControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/ciudad/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ciudadInstanceList.size() == 0
        assert model.ciudadInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.ciudadInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ciudadInstance != null
        assert view == '/ciudad/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ciudad/show/1'
        assert controller.flash.message != null
        assert Ciudad.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ciudad/list'

        populateValidParams(params)
        def ciudad = new Ciudad(params)

        assert ciudad.save() != null

        params.id = ciudad.id

        def model = controller.show()

        assert model.ciudadInstance == ciudad
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ciudad/list'

        populateValidParams(params)
        def ciudad = new Ciudad(params)

        assert ciudad.save() != null

        params.id = ciudad.id

        def model = controller.edit()

        assert model.ciudadInstance == ciudad
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ciudad/list'

        response.reset()

        populateValidParams(params)
        def ciudad = new Ciudad(params)

        assert ciudad.save() != null

        // test invalid parameters in update
        params.id = ciudad.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ciudad/edit"
        assert model.ciudadInstance != null

        ciudad.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ciudad/show/$ciudad.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ciudad.clearErrors()

        populateValidParams(params)
        params.id = ciudad.id
        params.version = -1
        controller.update()

        assert view == "/ciudad/edit"
        assert model.ciudadInstance != null
        assert model.ciudadInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ciudad/list'

        response.reset()

        populateValidParams(params)
        def ciudad = new Ciudad(params)

        assert ciudad.save() != null
        assert Ciudad.count() == 1

        params.id = ciudad.id

        controller.delete()

        assert Ciudad.count() == 0
        assert Ciudad.get(ciudad.id) == null
        assert response.redirectedUrl == '/ciudad/list'
    }
}
