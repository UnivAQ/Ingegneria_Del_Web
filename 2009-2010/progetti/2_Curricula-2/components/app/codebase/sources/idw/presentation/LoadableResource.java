package idw.presentation;

public class LoadableResource implements LoadableResourceInterface
{
        protected String _type;

        protected String _resource;

        public LoadableResource(String resource)
        {
                this._resource = resource;
        }

        @Override
        public String getType()
        {
                return this._type;
        }

        @Override
        public String getResource()
        {
                return this._resource;
        }
}
