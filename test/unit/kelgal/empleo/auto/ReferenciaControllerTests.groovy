package kelgal.empleo.auto

import kelgal.empleo.auto.ReferenciaController;
import kelgal.empleos.Referencia



@TestFor(ReferenciaController)
@Mock(Referencia)
class ReferenciaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/referencia/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.referenciaInstanceList.size() == 0
        assert model.referenciaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.referenciaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.referenciaInstance != null
        assert view == '/referencia/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/referencia/show/1'
        assert controller.flash.message != null
        assert Referencia.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/referencia/list'

        populateValidParams(params)
        def referencia = new Referencia(params)

        assert referencia.save() != null

        params.id = referencia.id

        def model = controller.show()

        assert model.referenciaInstance == referencia
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/referencia/list'

        populateValidParams(params)
        def referencia = new Referencia(params)

        assert referencia.save() != null

        params.id = referencia.id

        def model = controller.edit()

        assert model.referenciaInstance == referencia
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/referencia/list'

        response.reset()

        populateValidParams(params)
        def referencia = new Referencia(params)

        assert referencia.save() != null

        // test invalid parameters in update
        params.id = referencia.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/referencia/edit"
        assert model.referenciaInstance != null

        referencia.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/referencia/show/$referencia.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        referencia.clearErrors()

        populateValidParams(params)
        params.id = referencia.id
        params.version = -1
        controller.update()

        assert view == "/referencia/edit"
        assert model.referenciaInstance != null
        assert model.referenciaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/referencia/list'

        response.reset()

        populateValidParams(params)
        def referencia = new Referencia(params)

        assert referencia.save() != null
        assert Referencia.count() == 1

        params.id = referencia.id

        controller.delete()

        assert Referencia.count() == 0
        assert Referencia.get(referencia.id) == null
        assert response.redirectedUrl == '/referencia/list'
    }
}
