def build() {
    echo 'Building The App'
}
def test() {
    echo 'Testing The App'
}

def deploy() {
    echo "Deploying The App Version ${params.VERSION}"
}

return this